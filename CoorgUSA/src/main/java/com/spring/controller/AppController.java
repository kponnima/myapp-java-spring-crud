package com.spring.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Album;
import com.spring.model.Dislike;
import com.spring.model.Event;
import com.spring.model.Like;
import com.spring.model.News;
import com.spring.model.Photo;
import com.spring.model.PhotoBucket;
import com.spring.model.Post;
import com.spring.model.Reply;
import com.spring.model.Song;
import com.spring.model.SongBucket;
import com.spring.model.User;
import com.spring.model.Video;
import com.spring.model.VideoBucket;
import com.spring.service.AlbumService;
import com.spring.service.DislikeService;
import com.spring.service.EventService;
import com.spring.service.LikeService;
import com.spring.service.NewsService;
import com.spring.service.PhotoService;
import com.spring.service.PostService;
import com.spring.service.ReplyService;
import com.spring.service.SongService;
import com.spring.service.UserService;
import com.spring.service.VideoService;
import com.spring.validator.EventValidator;
import com.spring.validator.NewsValidator;
import com.spring.validator.PhotoValidator;
import com.spring.validator.PostValidator;
import com.spring.validator.ReplyValidator;
import com.spring.validator.SongValidator;
import com.spring.validator.UserFormValidator;
import com.spring.validator.VideoValidator;

@Controller
@RequestMapping("/")
@SessionAttributes("role")
public class AppController {

	static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	UserService userService;

	@Autowired
	NewsService newsService;

	@Autowired
	EventService eventService;

	@Autowired
	PostService postService;

	@Autowired
	ReplyService replyService;

	@Autowired
	LikeService likeService;

	@Autowired
	DislikeService dislikeService;

	@Autowired
	AlbumService albumService;

	@Autowired
	PhotoService photoService;

	@Autowired
	SongService songService;

	@Autowired
	VideoService videoService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserFormValidator userFormValidator;

	@Autowired
	NewsValidator newsvalidator;

	@Autowired
	EventValidator eventvalidator;

	@Autowired
	PostValidator postvalidator;

	@Autowired
	ReplyValidator replyvalidator;

	@Autowired
	PhotoValidator photofilevalidator;

	@Autowired
	SongValidator songfilevalidator;

	@Autowired
	VideoValidator videofilevalidator;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@InitBinder("user")
	protected void initBinderUser(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@InitBinder("photoBucket")
	protected void initBinderPhoto(WebDataBinder binder) {
		binder.setValidator(photofilevalidator);
	}

	@InitBinder("songBucket")
	protected void initBinderSong(WebDataBinder binder) {
		binder.setValidator(songfilevalidator);
	}

	@InitBinder("videoBucket")
	protected void initBinderVideo(WebDataBinder binder) {
		binder.setValidator(videofilevalidator);
	}

	@InitBinder("event")
	protected void initBinderEvent(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.setValidator(eventvalidator);
	}

	@InitBinder("post")
	protected void initBinderPost(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.setValidator(postvalidator);
	}

	@InitBinder("reply")
	protected void initBinderReply(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.setValidator(replyvalidator);
	}

	// display default page.
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String defaultPage(ModelMap model) {
		model.addAttribute("greeting", "Welcome To Coorg USA Site!");
		return "index";
	}

	// display home page.
	int count = 0;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		List<String> list = getfamilyList();
		model.addObject("lists", list);

		String username = getPrincipal();
		User user = userService.findByUserName(username);
		String firstname = user.getFirstname();
		if (count == 0) {
			model.addObject("welcomemsg", "Welcome <strong>" + firstname + "</strong>");
		}
		model.setViewName("home");
		count = count + 1;
		return model;
	}

	// Get matching member profile for search autocomplete
	@RequestMapping(value = { "/getUser" }, method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody List<String> getUser(@RequestParam("term") String query) {
		List<String> userList = userService.getUserList(query);
		return userList;
	}

	// display error page.
	@RequestMapping(value = { "/403" }, method = RequestMethod.GET)
	public String errorPage(ModelMap model) {
		model.addAttribute("exception.message", "Requested Page is not available at this time.Please try again !");
		return "error";
	}

	/********************************************************* REGISTRATION_LOGIN_HANDLERS ***************************************************************************/
	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	// POST request for saving user in database.
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String saveUser(@Validated User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		if (!userService.isUserNameUnique(user.getUserid(), user.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			return "registration";
		}
		userService.saveUser(user);
		userService.sendRegistrationConfirmation(user);

		model.addAttribute("success", "User " + user.getFirstname() + " registered successfully");
		return "redirect:/login?regsuccess";
	}

	// Handle logout request.
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/thankyou";
	}

	// Handle login request.
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "regsuccess", required = false) String regsuccess,
			@RequestParam(value = "passwordreset", required = false) String passwordreset, ModelMap model) {

		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		if (regsuccess != null) {
			model.addAttribute("success",
					"Congratulations! You've been successfully registered. Please login to continue");
		}
		if (passwordreset != null) {
			model.addAttribute("passwordreset", passwordreset);
		}
		return "login";
	}

	// Handle forgot password request.
	@RequestMapping(value = { "/forgotpswd" }, method = RequestMethod.GET)
	public String getForgotPswdPage(ModelMap model) {
		return "forgotpswd";
	}

	@RequestMapping(value = { "/forgotpswd" }, method = RequestMethod.POST)
	public String postForgotPswdPage(@RequestParam(value = "email", required = false) String email, ModelMap model) {

		if (!userService.isUserEmailUnique(email)) {
			model.addAttribute("error", "Entered email is not registered!");
			return "forgotpswd";
		}
		return "redirect:/updatepswd-" + email;
	}

	// Handle update password request.
	@RequestMapping(value = { "/updatepswd-{email}" }, method = RequestMethod.GET)
	public String getUpdatePswdPage(@PathVariable String email, ModelMap model) {
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		return "updatepswd";
	}

	@RequestMapping(value = { "/updatepswd-{email}" }, method = RequestMethod.POST)
	public String postUpdatePswdPage(User user, ModelMap model) {
		userService.updateUser(user);
		model.addAttribute("passwordreset", "Password reset successful!");
		return "redirect:/login";
	}

	/********************************************************************** ADMIN_REQUEST_HANDLERS *****************************************************************/
	// Fetch the userslist for ADMIN users
	@RequestMapping(value = { "/userslist" }, method = RequestMethod.GET)
	public ModelAndView listUsers(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "editsuccess", required = false) String editsuccess,
			@RequestParam(value = "deletesuccess", required = false) String deletesuccess) {

		ModelAndView model = new ModelAndView();
		List<User> users = userService.findAllUsers();
		model.addObject("users", users);
		model.addObject("loggedinuser", getPrincipal());
		if (error != null) {
			model.addObject("error", "Operation failed");
		}
		if (editsuccess != null) {
			model.addObject("editsuccess", "Edit user successful");
		}
		if (deletesuccess != null) {
			model.addObject("deletesuccess", "User has been deleted");
		}
		model.setViewName("userslist");
		return model;
	}

	// Fetch the user to edit.
	@RequestMapping(value = { "/edit-user-{userid}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable int userid, ModelMap model) {
		User user = userService.findById(userid);
		model.addAttribute("user", user);
		return "edituser";
	}

	// Update an existing user.
	@RequestMapping(value = { "/edit-user-{userid}" }, method = RequestMethod.POST)
	public String updateUser(User user, ModelMap model) {

		if (!userService.isUserNameUnique(user.getUserid(), user.getUsername())) {
			FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			model.addAttribute("error", usernameError);
			return "edituser";
		}

		userService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstname() + " " + user.getLastname() + " updated successfully");
		return "redirect:/usersList?editsuccess";
	}

	// Delete an user by ID
	@RequestMapping(value = { "/delete-user-{userid}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable int userid) {
		userService.deleteById(userid);
		return "redirect:/userslist?deletesuccess";
	}

	/********************************************************************** OTHER_HANDLERS *****************************************************************/
	// Search an existing user profile.
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String Search(@RequestParam("searchString") String searchString, Model model) {
		if (searchString != null) {
			Object searchResult = userService.findByFirstName(searchString);
			if (null == searchResult) {
				model.addAttribute("emptyuser", "emptyuser");
				model.addAttribute("searchString", searchString);
			} else {
				model.addAttribute("searcheduser", searchResult);
			}
		}
		return "search";
	}

	/************************** NEWS_HANDLERS **************************/
	// Fetch existing events list and display EVENT view.
	@RequestMapping(value = { "/news" }, method = RequestMethod.GET)
	public ModelAndView viewNews(Integer offset, Integer maxResults,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "adderror", required = false) String adderror,
			@RequestParam(value = "adderrorunique", required = false) String adderrorunique,
			@RequestParam(value = "addsuccess", required = false) String addsuccess) {

		News news = new News();
		User user = getUser();

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", error);
		}
		if (adderror != null) {
			model.addObject("adderror", adderror);
		}
		if (adderrorunique != null) {
			model.addObject("adderrorunique", adderrorunique);
		}
		if (addsuccess != null) {
			model.addObject("addsuccess", addsuccess);
		}

		model.addObject("news", news);
		model.addObject("user", user);
		model.addObject("articles", newsService.getNews(offset, maxResults));
		model.addObject("count", newsService.count());
		model.addObject("offset", offset);
		model.setViewName("news");
		return model;
	}

	// Save created post to database.
	@RequestMapping(value = "/news", method = RequestMethod.POST)
	public String saveNews(@Valid News news, BindingResult result, ModelMap model) throws IOException {

		String username = getPrincipal();
		User user = userService.findByUserName(username);

		if (result.hasErrors()) {
			model.addAttribute("adderror", "Unable to save article with the data provided!");
			return "redirect:/news";
		} else {
			if (!newsService.isNewsUnique(news.getNewsid(), news.getTitle())) {
				FieldError adderror = new FieldError("post", "title", messageSource.getMessage("non.unique.post",
						new String[] { news.getTitle() }, Locale.getDefault()));
				result.addError(adderror);
				model.addAttribute("adderrorunique", "Entered news article already exists. Please check the inputs.");
				return "redirect:/blogs";
			}
			saveNews(news, user);
			model.addAttribute("addsuccess", "Article has been successfully created");
			return "redirect:/news";
		}
	}

	private void saveNews(News news, User user) throws IOException {
		news.setUser(user);
		news.setTitle(news.getTitle());
		news.setBody(news.getBody());
		newsService.save(news);
	}

	/***************************** EVENT_HANDLERS *****************************/

	// Fetch existing events list and display EVENT view.
	@RequestMapping(value = { "/events" }, method = RequestMethod.GET)
	public ModelAndView viewEvents(Integer offset, Integer maxResults,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "adderror", required = false) String adderror,
			@RequestParam(value = "adderrorunique", required = false) String adderrorunique,
			@RequestParam(value = "addsuccess", required = false) String addsuccess,
			@RequestParam(value = "editerror", required = false) String editerror,
			@RequestParam(value = "editsuccess", required = false) String editsuccess,
			@RequestParam(value = "deletesuccess", required = false) String deletesuccess,
			@RequestParam(value = "emailsuccess", required = false) String emailsuccess,
			@RequestParam(value = "emailerror", required = false) String emailerror) {

		Event event = new Event();
		List<String> list = getEventLocationList();
		User user = getUser();

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", error);
		}
		if (adderror != null) {
			model.addObject("adderror", adderror);
		}
		if (adderrorunique != null) {
			model.addObject("adderrorunique", adderrorunique);
		}
		if (addsuccess != null) {
			model.addObject("addsuccess", addsuccess);
		}
		if (editerror != null) {
			model.addObject("editerror", "Edit operation failed");
		}
		if (editsuccess != null) {
			model.addObject("editsuccess", "Edit event successful");
		}
		if (deletesuccess != null) {
			model.addObject("deletesuccess", "Selected event has been successfully deleted");
		}
		if (emailsuccess != null) {
			model.addObject("emailsuccess", "Email has been sent to the recipients.");
		}
		if (emailerror != null) {
			model.addObject("emailerror", "Email not sent.Please check the inputs and try again!");
		}
		model.addObject("user", user);
		model.addObject("eventlocationList", list);
		model.addObject("event", event);
		model.addObject("events", eventService.getEvents(offset, maxResults));
		model.addObject("count", eventService.count());
		model.addObject("offset", offset);
		model.setViewName("events");
		return model;
	}

	// Save an event to database.
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public String saveEvent(@Valid Event event, BindingResult result, ModelMap model) throws IOException {

		User user = getUser();

		if (result.hasErrors()) {
			model.addAttribute("adderror",
					"Unable to save event with the data provided! Ensure the date is not in the past.");
			return "redirect:/events";
		} else {
			if (!eventService.isEventUnique(event.getId(), event.getTitle(), event.getDate(), event.getLocation())) {
				FieldError adderror = new FieldError("event", "title", messageSource.getMessage("non.unique.event",
						new String[] { event.getTitle() }, Locale.getDefault()));
				result.addError(adderror);
				model.addAttribute("adderrorunique", "Entered event already exists. Please check the inputs.");
				return "redirect:/events";
			}
			saveEvent(event, user);
			model.addAttribute("addsuccess", "Event has been successfully created");
			return "redirect:/events";
		}
	}

	// Edit existing event.
	@RequestMapping(value = { "/edit-event-{id}" }, method = RequestMethod.GET)
	public String editEvent(@PathVariable int id, ModelMap model) {
		Event event = eventService.findById(id);
		List<String> list = getEventLocationList();

		model.addAttribute("event", event);
		model.addAttribute("eventlocationList", list);
		return "editevent";
	}

	// Update an existing event.
	@RequestMapping(value = { "/edit-event-{id}" }, method = RequestMethod.POST)
	public String updateEvent(Event event, ModelMap model) {
		if (!eventService.isEventUnique(event.getId(), event.getTitle(), event.getDate(), event.getLocation())) {
			model.addAttribute("adderrorunique", "Entered event already exists. Please check the inputs.");
			return "redirect:/events?editerror";
		}
		eventService.updateEvent(event);
		model.addAttribute("editsuccess", "Event " + event.getId() + " " + event.getTitle() + " updated successfully");
		return "redirect:/events?editsuccess";
	}

	// Delete an existing event by ID */
	@RequestMapping(value = { "/delete-event-{id}" }, method = RequestMethod.GET)
	public String deleteEvent(@PathVariable int id) {
		eventService.deleteById(id);
		return "redirect:/events?deletesuccess";
	}

	@RequestMapping(value = { "/sendmail" }, method = RequestMethod.POST)
	public String doSendEmail(HttpServletRequest request) {
		String host = "smtp.gmail.com";
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		String from = "kushalappapp@gmail.com"; // Your E-mail Id
		String to = request.getParameter("event-Toemail");
		String cc = request.getParameter("event-CCemail");
		String subject = request.getParameter("event-subject");
		String messageText = request.getParameter("event-message");
		boolean sessionDebug = true;

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "465");// port number 465,587
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.debug", "true");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));

			if (to.contains(",")) {
				String[] toList = to.split(",");
				InternetAddress[] toaddressArray = new InternetAddress[toList.length];

				int counter = 0;
				for (String recipient : toList) {
					toaddressArray[counter] = new InternetAddress(recipient.trim());
					counter++;
				}
				msg.setRecipients(Message.RecipientType.TO, toaddressArray);

			} else {
				InternetAddress[] toaddress = { new InternetAddress(to) };
				msg.setRecipients(Message.RecipientType.TO, toaddress);
			}
			if ((null != cc) && (!cc.isEmpty())) {
				if (cc.contains(",")) {
					String[] ccList = cc.split(",");
					InternetAddress[] ccaddressArray = new InternetAddress[ccList.length];

					int counter = 0;
					for (String recipient : ccList) {
						ccaddressArray[counter] = new InternetAddress(recipient.trim());
						counter++;
					}
					msg.setRecipients(Message.RecipientType.CC, ccaddressArray);
				} else {
					InternetAddress[] ccaddress = { new InternetAddress(cc) };
					msg.setRecipients(Message.RecipientType.CC, ccaddress);
				}
			}

			msg.setSubject(subject);
			msg.setContent(messageText, "text/html");

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, "kushalappapp@gmail.com", "Gmail@86");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return "redirect:/events?emailsuccess";
		} catch (Exception e) {
			return "redirect:/events?emailerror";
		}
	}

	/*
	 * @RequestMapping(value = { "/sendmail" }, method = RequestMethod.POST)
	 * public String doSendEmail(HttpServletRequest request) { // takes input
	 * from e-mail form String recipientToAddress =
	 * request.getParameter("event-Toemail"); String recipientCcAddress =
	 * request.getParameter("event-CCemail"); String subject =
	 * request.getParameter("event-subject"); String message =
	 * request.getParameter("event-message");
	 * 
	 * System.setProperty("java.net.preferIPv4Stack", "true");
	 * 
	 * // creates a simple e-mail object SimpleMailMessage email = new
	 * SimpleMailMessage(); email.setTo(recipientToAddress); if (null !=
	 * recipientCcAddress && recipientCcAddress.length() > 0) {
	 * email.setCc(recipientCcAddress); } email.setSubject(subject);
	 * email.setText(message);
	 * 
	 * // sends the e-mail mailSender.send(email); return
	 * "redirect:/events?emailsuccess"; }
	 */

	private void saveEvent(Event event, User user) throws IOException {
		event.setUser(user);
		event.setTitle(event.getTitle());
		event.setDate(event.getDate());
		event.setLocation(event.getLocation());
		eventService.save(event);
	}

	/************************** BLOG_HANDLERS **************************/
	// Fetch existing events list and display EVENT view.
	@RequestMapping(value = { "/blogs" }, method = RequestMethod.GET)
	public ModelAndView viewPosts(Integer offset, Integer maxResults,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "adderror", required = false) String adderror,
			@RequestParam(value = "adderrorunique", required = false) String adderrorunique,
			@RequestParam(value = "addsuccess", required = false) String addsuccess,
			@RequestParam(value = "editerror", required = false) String editerror,
			@RequestParam(value = "editsuccess", required = false) String editsuccess,
			@RequestParam(value = "deletesuccess", required = false) String deletesuccess,
			@RequestParam(value = "addreplyerror", required = false) String addreplyerror,
			@RequestParam(value = "addreplysuccess", required = false) String addreplysuccess,
			@RequestParam(value = "deletereplysuccess", required = false) String deletereplysuccess,
			@RequestParam(value = "addlikeerror", required = false) String addlikeerror,
			@RequestParam(value = "addlikesuccess", required = false) String addlikesuccess,
			@RequestParam(value = "adddislikeerror", required = false) String adddislikeerror,
			@RequestParam(value = "adddislikesuccess", required = false) String adddislikesuccess) {

		Post post = new Post();
		User user = getUser();
		Reply reply = new Reply();

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", error);
		}
		if (adderror != null) {
			model.addObject("adderror", adderror);
		}
		if (adderrorunique != null) {
			model.addObject("adderrorunique", adderrorunique);
		}
		if (addsuccess != null) {
			model.addObject("addsuccess", addsuccess);
		}
		if (editerror != null) {
			model.addObject("editerror", "Edit operation failed");
		}
		if (editsuccess != null) {
			model.addObject("editsuccess", "Edit event successful");
		}
		if (deletesuccess != null) {
			model.addObject("deletesuccess", "Selected event has been successfully deleted");
		}
		if (addreplyerror != null) {
			model.addObject("addreplyerror", addreplyerror);
		}
		if (addreplysuccess != null) {
			model.addObject("addreplysuccess", addreplysuccess);
		}
		if (deletereplysuccess != null) {
			model.addObject("deletereplysuccess", "Selected post reply has been successfully deleted");
		}
		if (addlikeerror != null) {
			model.addObject("addlikeerror", "User has already liked the post");
		}
		if (addlikesuccess != null) {
			model.addObject("addlikesuccess", "Like successful");
		}
		if (adddislikeerror != null) {
			model.addObject("adddislikeerror", "User has already disliked the post");
		}
		if (adddislikesuccess != null) {
			model.addObject("adddislikesuccess", "Dislike successful");
		}

		model.addObject("user", user);
		model.addObject("post", post);
		model.addObject("posts", postService.getPosts(offset, maxResults));
		model.addObject("count", postService.count());
		model.addObject("offset", offset);
		model.addObject("reply", reply);
		model.addObject("replies", replyService.getAllReply());
		model.addObject("likes", likeService.getAllLike());
		model.addObject("dislikes", dislikeService.getAllDislike());
		model.setViewName("blogs");
		return model;
	}

	// Save created post to database.
	@RequestMapping(value = "/blogs", method = RequestMethod.POST)
	public String savePost(@Valid Post post, BindingResult result, ModelMap model) throws IOException {

		String username = getPrincipal();
		User user = userService.findByUserName(username);

		if (result.hasErrors()) {
			model.addAttribute("adderror", "Unable to save post with the data provided!");
			return "redirect:/blogs";
		} else {
			if (!postService.isPostUnique(post.getPostid(), post.getTitle())) {
				FieldError adderror = new FieldError("post", "title", messageSource.getMessage("non.unique.post",
						new String[] { post.getTitle() }, Locale.getDefault()));
				result.addError(adderror);
				model.addAttribute("adderrorunique", "Entered post already exists. Please check the inputs.");
				return "redirect:/blogs";
			}
			savePost(post, user);
			model.addAttribute("addsuccess", "Post has been successfully created");
			return "redirect:/blogs";
		}
	}

	// Edit existing post.
	@RequestMapping(value = { "/edit-post-{postid}" }, method = RequestMethod.GET)
	public String editPost(@PathVariable int postid, ModelMap model) {
		Post post = postService.findById(postid);
		model.addAttribute("post", post);
		return "editpost";
	}

	// Update an existing post.
	@RequestMapping(value = { "/edit-post-{postid}" }, method = RequestMethod.POST)
	public String updatePost(Post post, ModelMap model, @PathVariable int postid) {
		if (!postService.isPostUnique(post.getPostid(), post.getTitle())) {
			model.addAttribute("adderrorunique", "Entered event already exists. Please check the inputs.");
			return "redirect:/blogs?editerror";
		}
		postService.updatePost(post);
		model.addAttribute("editsuccess", "Post " + post.getPostid() + " " + post.getTitle() + " updated successfully");
		return "redirect:/blogs?editsuccess";
	}

	// Delete an existing post by ID */
	@RequestMapping(value = { "/delete-post-{postid}" }, method = RequestMethod.GET)
	public String deletePost(@PathVariable int postid) {
		postService.deleteById(postid);
		return "redirect:/blogs?deletesuccess";
	}

	private void savePost(Post post, User user) throws IOException {
		post.setUser(user);
		post.setTitle(post.getTitle());
		post.setBody(post.getBody());
		post.setLikes(0);
		post.setDislikes(0);
		post.setReplies(0);
		postService.save(post);
	}

	// Reply to existing post.
	@RequestMapping(value = { "/reply-post-{postid}" }, method = RequestMethod.POST)
	public String saveReply(@PathVariable int postid, @Validated Reply reply, BindingResult result, ModelMap model)
			throws IOException {

		Post post = postService.findById(postid);
		String username = getPrincipal();
		User user = userService.findByUserName(username);

		if (result.hasErrors()) {
			model.addAttribute("addreplyerror", "Unable to save reply to the post with the data provided!");
			return "redirect:/blogs";
		}

		saveReply(reply, post, user);
		model.addAttribute("addreplysuccess", "Reply posted successfully");
		return "redirect:/blogs";
	}

	// Delete an existing reply by ID */
	@RequestMapping(value = { "/delete-reply-{replyid}" }, method = RequestMethod.GET)
	public String deleteReply(@PathVariable int replyid) {
		replyService.deleteById(replyid);
		return "redirect:/blogs?deletereplysuccess";
	}

	private void saveReply(Reply reply, Post post, User user) throws IOException {
		reply.setBody(reply.getBody());
		reply.setPost(post);
		reply.setUser(user);
		replyService.save(reply);
	}

	// Like an existing post.
	@RequestMapping(value = { "/like-post-{postid}" }, method = RequestMethod.GET)
	public String likePost(@PathVariable int postid) throws IOException {

		Post post = postService.findById(postid);
		String username = getPrincipal();
		User user = userService.findByUserName(username);
		Integer userid = user.getUserid();

		if (!likeService.isLikeUnique(userid, postid)) {
			return "redirect:/blogs?addlikeerror";
		}

		Like like = new Like();
		saveLike(like, post, user);
		return "redirect:/blogs?addlikesuccess";
	}

	private void saveLike(Like like, Post post, User user) throws IOException {
		like.setPost(post);
		like.setUser(user);
		likeService.save(like);
	}

	// Disike an existing post.
	@RequestMapping(value = { "/dislike-post-{postid}" }, method = RequestMethod.GET)
	public String dislikePost(@PathVariable int postid) throws IOException {

		Post post = postService.findById(postid);
		String username = getPrincipal();
		User user = userService.findByUserName(username);
		Integer userid = user.getUserid();

		if (!dislikeService.isDislikeUnique(userid, postid)) {
			return "redirect:/blogs?adddislikeerror";
		}

		Dislike dislike = new Dislike();
		saveDislike(dislike, post, user);
		return "redirect:/blogs?adddislikesuccess";
	}

	private void saveDislike(Dislike dislike, Post post, User user) throws IOException {
		dislike.setPost(post);
		dislike.setUser(user);
		dislikeService.save(dislike);
	}

	/************************** PHOTO_HANDLERS **************************/
	@RequestMapping(value = { "/albums" }, method = RequestMethod.GET)
	public String viewAlbums(ModelMap model) {

		Album album = new Album();
		model.addAttribute("album", album);

		List<Album> albums = albumService.getAllAlbums();
		model.addAttribute("albums", albums);

		return "albums";
	}

	@RequestMapping(value = { "/add-album" }, method = RequestMethod.POST)
	public String addAlbum(Album album, HttpServletResponse response) throws IOException {

		String username = getPrincipal();
		User user = userService.findByUserName(username);

		saveAlbum(album, user);
		return "redirect:/albums";
	}

	private void saveAlbum(Album album, User user) throws IOException {

		album.setUser(user);
		album.setTitle(album.getTitle());
		album.setDescription(album.getDescription());
		albumService.save(album);
	}

	@RequestMapping(value = { "/view-photo-{albumId}" }, method = RequestMethod.GET)
	public String viewPhotos(@RequestParam(value = "adderror", required = false) String adderror,
			@RequestParam(value = "typeerror", required = false) String typeerror, @PathVariable int albumId,
			HttpServletResponse response, ModelMap model) throws IOException {

		if (adderror != null) {
			model.addAttribute("adderror", adderror);
		}
		if (typeerror != null) {
			model.addAttribute("typeerror", typeerror);
		}

		List<Photo> photos = photoService.findAllByAlbumId(albumId);
		model.addAttribute("photos", photos);

		PhotoBucket photoBucket = new PhotoBucket();
		model.addAttribute("photoBucket", photoBucket);

		Album album = albumService.findById(albumId);
		model.addAttribute("album", album);

		String username = getPrincipal();
		User user = userService.findByUserName(username);
		model.addAttribute("user", user);

		return "/photos";
	}

	@RequestMapping(value = { "/add-photo-{albumId}" }, method = RequestMethod.POST)
	public String uploadPhoto(@PathVariable int albumId, @Valid PhotoBucket photoBucket, BindingResult result,
			ModelMap model) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("adderror", "Unable to upload photo!");
			return "redirect:/view-photo-" + albumId;
		}

		String username = getPrincipal();
		User user = userService.findByUserName(username);
		Album album = albumService.findById(albumId);

		savePhoto(photoBucket, user, album);
		return "redirect:/view-photo-" + albumId;
	}

	@RequestMapping(value = { "/download-photo-{albumId}-{imageid}" }, method = RequestMethod.GET)
	public void downloadPhoto(@PathVariable int albumId, @PathVariable int imageid, HttpServletResponse response)
			throws IOException {
		Photo photo = photoService.findById(imageid);
		response.setContentType(photo.getType());
		response.setContentLength(photo.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + photo.getTitle() + "\"");

		FileCopyUtils.copy(photo.getContent(), response.getOutputStream());
	}

	@RequestMapping(value = { "/delete-photo-{albumId}-{imageid}" }, method = RequestMethod.GET)
	public String deletePhoto(@PathVariable int albumId, @PathVariable int imageid) {
		photoService.deleteById(imageid);
		return "redirect:/view-photo-" + albumId;
	}

	private void savePhoto(PhotoBucket photoBucket, User user, Album album) throws IOException {

		Photo photo = new Photo();
		MultipartFile multipartFile = photoBucket.getPhotofile();
		photo.setUser(user);
		photo.setAlbum(album);
		photo.setTitle(multipartFile.getOriginalFilename());
		photo.setDescription(photoBucket.getDescription());
		photo.setType(multipartFile.getContentType());
		photo.setContent(multipartFile.getBytes());
		photoService.savePhoto(photo);
	}

	/************************** SONG_HANDLERS **************************/

	@RequestMapping(value = { "/songs" }, method = RequestMethod.GET)
	public String viewSongs(@RequestParam(value = "adderror", required = false) String adderror,
			@RequestParam(value = "typeerror", required = false) String typeerror, HttpServletResponse response,
			ModelMap model) {

		if (adderror != null) {
			model.addAttribute("adderror", adderror);
		}
		if (typeerror != null) {
			model.addAttribute("typeerror", typeerror);
		}

		String username = getPrincipal();
		User user = userService.findByUserName(username);
		model.addAttribute("user", user);

		SongBucket songBucket = new SongBucket();
		model.addAttribute("songBucket", songBucket);

		List<Song> songs = songService.findAll();
		model.addAttribute("songs", songs);

		return "songs";
	}

	@RequestMapping(value = { "/songs" }, method = RequestMethod.POST)
	public String uploadSong(@Valid SongBucket songBucket, BindingResult result, ModelMap model) throws IOException {

		String username = getPrincipal();
		User user = userService.findByUserName(username);

		if (result.hasErrors()) {
			model.addAttribute("adderror", "Unable to upload song!");
			return "redirect:/songs";
		}
		saveSong(songBucket, user);
		return "redirect:/songs";
	}

	@RequestMapping(value = { "/download-song-{songid}" }, method = RequestMethod.GET)
	public void downloadSong(@PathVariable int songid, HttpServletResponse response) throws IOException {
		Song song = songService.findById(songid);
		response.setContentType(song.getType());
		response.setContentLength(song.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + song.getTitle() + "\"");

		FileCopyUtils.copy(song.getContent(), response.getOutputStream());
	}

	@RequestMapping(value = { "/delete-song-{songid}" }, method = RequestMethod.GET)
	public String deleteSong(@PathVariable int songid) {
		songService.deleteById(songid);
		return "redirect:/songs";
	}

	private void saveSong(SongBucket songBucket, User user) throws IOException {

		Song song = new Song();
		MultipartFile multipartFile = songBucket.getSongfile();

		song.setTitle(multipartFile.getOriginalFilename());
		song.setDescription(songBucket.getDescription());
		song.setType(multipartFile.getContentType());
		song.setContent(multipartFile.getBytes());
		song.setUser(user);
		songService.saveSong(song);
	}

	/************************** VIDEO_HANDLERS **************************/

	@RequestMapping(value = { "/videos" }, method = RequestMethod.GET)
	public String viewVidoes(@RequestParam(value = "adderror", required = false) String adderror,
			@RequestParam(value = "typeerror", required = false) String typeerror, HttpServletResponse response,
			ModelMap model) {

		if (adderror != null) {
			model.addAttribute("adderror", adderror);
		}
		if (typeerror != null) {
			model.addAttribute("typeerror", typeerror);
		}

		String username = getPrincipal();
		User user = userService.findByUserName(username);
		model.addAttribute("user", user);

		VideoBucket videoBucket = new VideoBucket();
		model.addAttribute("videoBucket", videoBucket);

		List<Video> videos = videoService.findAll();
		model.addAttribute("videos", videos);

		return "videos";
	}

	@RequestMapping(value = { "/videos" }, method = RequestMethod.POST)
	public String uploadVideo(@Valid VideoBucket videoBucket, BindingResult result, ModelMap model) throws IOException {

		String username = getPrincipal();
		User user = userService.findByUserName(username);

		if (result.hasErrors()) {
			model.addAttribute("adderror", "Unable to upload video!");
			return "redirect:/videos";
		} else {
			saveVideo(videoBucket, user);
			return "redirect:/videos";
		}
	}

	@RequestMapping(value = { "/download-video-{videoid}" }, method = RequestMethod.GET)
	public void downloadVideo(@PathVariable int videoid, HttpServletResponse response) throws IOException {
		Video video = videoService.findById(videoid);
		response.setContentType(video.getType());
		response.setContentLength(video.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + video.getTitle() + "\"");

		FileCopyUtils.copy(video.getContent(), response.getOutputStream());
	}

	@RequestMapping(value = { "/delete-video-{videoid}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int videoid) {
		videoService.deleteById(videoid);
		return "redirect:/videos";
	}

	private void saveVideo(VideoBucket videoBucket, User user) throws IOException {

		Video video = new Video();
		MultipartFile multipartFile = videoBucket.getVideofile();

		video.setTitle(multipartFile.getOriginalFilename());
		video.setDescription(videoBucket.getDescription());
		video.setType(multipartFile.getContentType());
		video.setContent(multipartFile.getBytes());
		video.setUser(user);
		videoService.saveVideo(video);
	}

	/************************** MISC_HANDLERS_METHODS **************************/

	// Method returns the principal[user-name] of logged-in user.
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private User getUser() {
		String username = getPrincipal();
		User user = userService.findByUserName(username);
		return user;
	}

	private List<String> getfamilyList() {
		List<String> list = new ArrayList<String>();
		list.add("Ponnimada");
		list.add("Baleyada");
		list.add("Madrira");
		list.add("Shivachalianda");
		list.add("Palaganda");
		list.add("Kuppanda");
		list.add("Mukkatira");
		list.add("Muruvanda");
		list.add("Kokkengada");
		list.add("Machimada");
		return list;
	}

	private List<String> getEventLocationList() {
		List<String> list = new ArrayList<String>();
		list.add("Albuquerque");
		list.add("California");
		list.add("Denver");
		list.add("New York");
		list.add("Phoenix");
		list.add("Seattle");
		return list;
	}

}