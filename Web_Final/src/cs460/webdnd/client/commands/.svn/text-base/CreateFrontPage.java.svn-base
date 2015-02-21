package cs460.webdnd.client.commands;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.PrintCanvas;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;

import cs460.webdnd.client.handlers.NewUserHandler;
import cs460.webdnd.client.mvc.controller.IController;

/**
 * This is the main frontpage for the website, 
 * this builds each of the individual pages and yada yada yada
 * 
 * @author niallschavez
 */
public class CreateFrontPage {
	protected PrintCanvas frontPageCanvas;
	protected Canvas loginBox;
	protected IController cont;

	public CreateFrontPage(Canvas loginBox, IController cont) {
		this.loginBox = loginBox;
		this.frontPageCanvas = new PrintCanvas();
		this.cont = cont;
		generateHTML();
	}

	private void generateHTML() {

		frontPageCanvas.setBackgroundColor("#838B8B");
		frontPageCanvas.setWidth100();
		frontPageCanvas.setHeight("250%");

		final Canvas centerCanvas = new Canvas();

		centerCanvas.setBackgroundColor("black");
		centerCanvas.setWidth("80%");
		centerCanvas.setHeight100();
		centerCanvas.setLeft("10%");

		/**
		 * Initializing the body
		 */
		final PrintCanvas body = new PrintCanvas();

		body.setStyleName("body-style");

		body.setBorder("");
		body.setHeight("250%");
		body.setTop("4%");
		body.setBackgroundColor("#525252");
		body.setLeft("10%");
		body.setWidth("80%");

		HTML content = new HTML();
		content.setWidth("100%");
		content.setHeight("100%");
		content.setStyleName("content-style");

		/**************
		 * Front page *
		 **************/

		content.setHTML("<h2><center>Welcome to WebCreate!</center></h2><br><p class=home>Here you will find the tools you need as a small business owner, or web-savy "
				+ "individual to create your own professional looking website in just a few clicks. Using our proprietary software, "
				+ "we provide you with the ability to design, create, populate, and distribute your very own custom website in just 5 simple steps! "
				+ "Feel free to browse our site, create an account, and prepare to be amazed at the potential creativity that is "
				+ "inside you!"
				+ "<br> <br> "
				+ "<section class=\"image-gallery group\">"
				+ "<figure tabindex=\"1\">"
				+ "	<img src=\"http://i.imgur.com/wM3bL.png\" alt=\"Sample page\"/>"
				+ "</section>"
				+

				"<br><br>"
				+ "<br><br> <center>As you can see our editor provides robust tools to modify every possible attribute of your website! </center>"
				+ "<br><br> <p class= home> The amazing thing about this tool is that we are offering it <b>Free</b> while other website creation tool advertise themselves"
				+ "as being free, they hide content from you and give you stripped down versions of their software that limits your ability to create! Not WebCreate! "
				+ "We offer all of our tools at no cost to the user. <br><br> You may be asking your self how we can do this? Well the answer is simple, this site was written"
				+ "by college students as a class project. Therefore, even if we had solved the NP complete problem, we couldn't sell it because this "
				+ "code, though we wrote it, is owned by the university. So take advantage of forced labor and enjoy an awesome product!"
				+ "<h2><center>Testimonials by WebCreate users!</h2> "
				+

				"<center><p class= testmon1><img src=\"http://www.karatekidzonline.com/images/chucknorris00.jpg\" width=150px height=200px><br> \"I have used a lot of website creation tools in my day<br>"
				+ "but none could offer what I wanted, so I<br> ended up round-house kicking a lot of monitors, this <br> was quite costly. WebCreate was the first website creation tool<br> "
				+ "that didnt make me want to kill something.\" -Chuck Norris<br></p> "
				+ "<p class= testmon2> <img src= \"http://www.dst-corp.com/james/PaintingsOfJesus/Jesus14.jpg\"width=150px height=200px> \"You would think that after creating the heavens and the earth"
				+ "that I would have created a better way of making websites, but do you know how many computers I cursed (like I did that fig tree back in the day) trying to get my website (jesusrocks.com) "
				+ "looking the way I wanted it to look? Too many. But once I started using WebCreate, all my problems just dissapeared. Anyone who doesnt use this problem is definitely sentenced to eternity in Hades.\"-Jesus </p>");

		body.addChild(content);
		frontPageCanvas.addChild(centerCanvas);
		frontPageCanvas.addChild(body);

		/**
		 * end initialization
		 */

		final MenuBar menu = new MenuBar();
		menu.addStyleName("demo-MenuBar");

		menu.setWidth("100%");
		/*******************
		 * The "home page" *
		 *******************/
		menu.addItem("Home", new Command() {

			@Override
			public void execute() {

				Canvas[] cs = body.getChildren();
				body.removeChild(cs[0]);
				body.setLeft("10%");
				body.setWidth("80%");
				body.setHeight("250%");
				centerCanvas.setWidth("80%");
				HTML content = new HTML();
				content.setWidth("100%");
				content.setHeight("100%");
				content.setStyleName("content-style");
				frontPageCanvas.setHeight("250%");

				content.setHTML("<h2><center>Welcome to WebCreate!</center></h2><br><p class=home>Here you will find the tools you need as a small business owner, or web-savy "
						+ "individual to create your own professional looking website in just a few clicks. Using our proprietary software, "
						+ "we provide you with the ability to design, create, populate, and distribute your very own custom website in just 5 simple steps! "
						+ "Feel free to browse our site, create an account, and prepare to be amazed at the potential creativity that is "
						+ "inside you!"
						+ "<br> <br> "
						+ "<section class=\"image-gallery group\">"
						+ "<figure tabindex=\"1\">"
						+ "	<img src=\"http://i.imgur.com/wM3bL.png\" alt=\"Sample page\"/>"
						+ "</section>"
						+

						"<br><br>"
						+ "<br><br> <center>As you can see our editor provides robust tools to modify every possible attribute of your website! </center>"
						+ "<br><br> <p class= home> The amazing thing about this tool is that we are offering it <b>Free</b> while other website creation tool advertise themselves"
						+ "as being free, they hide content from you and give you stripped down versions of their software that limits your ability to create! Not WebCreate! "
						+ "We offer all of our tools at no cost to the user. <br><br> You may be asking your self how we can do this? Well the answer is simple, this site was written"
						+ "by college students as a class project. Therefore, even if we had solved the NP complete problem, we couldn't sell it because this "
						+ "code, though we wrote it, is owned by the university. So take advantage of forced labor and enjoy an awesome product!"
						+ "<h2><center>Testimonials by WebCreate users!</h2> "
						+

						"<center><p class= testmon1><img src=\"http://www.karatekidzonline.com/images/chucknorris00.jpg\" width=150px height=200px><br> \"I have used a lot of website creation tools in my day<br>"
						+ "but none could offer what I wanted, so I<br> ended up round-house kicking a lot of monitors, this <br> was quite costly. WebCreate was the first website creation tool<br> "
						+ "that didnt make me want to kill something.\" -Chuck Norris<br></p> "
						+ "<p class= testmon2> <img src= \"http://www.dst-corp.com/james/PaintingsOfJesus/Jesus14.jpg\"width=150px height=200px> \"You would think that after creating the heavens and the earth"
						+ "that I would have created a better way of making websites, but do you know how many computers I cursed (like I did that fig tree back in the day) trying to get my website (jesusrocks.com) "
						+ "looking the way I wanted it to look? Too many. But once I started using WebCreate, all my problems just dissapeared. Anyone who doesnt use this problem is definitely sentenced to eternity in Hades.\"-Jesus </p>");

				body.addChild(content);
				frontPageCanvas.redraw();
			}

		});

		/***************
		 * FAQ section *
		 ***************/
		menu.addItem("FAQ", new Command() {

			@Override
			public void execute() {
				Canvas[] cs = body.getChildren();
				body.removeChild(cs[0]);
				body.setWidth("60%");
				body.setLeft("20%");

				frontPageCanvas.setHeight("300%");
				HTML content = new HTML();

				content.setWidth("100%");
				content.setHeight("100%");

				content.setStyleName("content-style");
				content.setHTML(""
						+

						"<center>Welcome to WebCreate.com!</center><br> <p class=faq>Thank you for trying our product, and we hope "
						+ "that you are finding everything you need to make your website your own. "
						+ "In the event that you are new to website creation and how to use tools "
						+ "like these, hopefully this tutorial will be everything you need to get "
						+ "started creating your own website."
						+

						"<br><br>Step 1. Create a new project.<br><br>"
						+

						"This phase is crucial because it is here that you begin your quest for ultimate "
						+ "website creation. When you first log in you will be prompted to either choose "
						+ "an existing project or start a new project."
						+

						"<br><br>Step 2. A new beginning<br><br>"
						+

						"When you first bring up the editor you will notice a few things at a glance. "
						+ "To your left you will see the main toolbar for your project. Here all your "
						+ "editing tools are conveniently located for easy access and instant creation. "
						+ "We will break down each of these tools individually later in the tutorial. "
						+ "As we continue on, in the middle of the page you will see your site creation "
						+ "canvas. This is where you will be placing all the elements that will eventually "
						+ "populate your website. You will notice three tabs at the top of the screen. "
						+ "The first is the master template tab. This tab like all others contains a "
						+ "header, body, and footer, but what is different about this one is that when "
						+ "edited, you will notice that the styles objects and items that you add into "
						+ "this pane will populate across all other created panes. This is important "
						+ "because it is here that you set up your main style the site. But the caveat "
						+ "is that this page will not overwrite any other changes made to subsequently "
						+ "created pages, and the styles added here may only be edited from this page. "
						+

						"<br><br>Next you will see the the index tab, this is a re-nameable tab is the "
						+ "homepage for your website, this will be the first thing that customers and "
						+ "visitors will see when navigating to your website, so it is crucial to make "
						+ "this page pop out and make people want to come back here."
						+

						"<br><br>Continuing on, we see the New Tab option, here once clicked you will "
						+ "be prompted to enter a page name, this name translates to the name of the page "
						+ "within your site you will be navigating to. Looking towards the middle of the "
						+ "screen you will see that the pane is divided up into three sections, "
						+ "these sections are denoted as the header the body, and the footer, each of these "
						+ "sections bounds whatever elements that are added into them to that individual "
						+ "pane. thereby splitting your page up into three separate parts, each of these "
						+ "panes can be re sized and modified to fit your individual needs Finally, on "
						+ "the right side of the page, you will see the properties panel, this panel allows you "
						+ "to modify any of the objects that you have placed into your website, from "
						+ "color to opacity, to positioning,this is the one-stop-shop for all your "
						+ "modification needs. "
						+

						"<br><br> Step 3. A list of commands<br><br>"
						+ "As you begin to use this tool, you may or may not know about all the "
						+ "different options for editing objects within your development environment."
						+ "Here you will learn all about how modify the objects that you have placed"
						+ "into each canvas. </p>"
						+ "<pre style=\"font-family:courier\" size =\"16\">"
						+ "	Top Tab Set( the tabs that denote the various pages in your website) <br>"
						+ "		Master template tab<br>"
						+ "			click: selects tab<br>"
						+ "		Created tab<br>"
						+ "			click: selects tab<br>"
						+ "			right-click: edits tab name<br>"
						+ "			hover, click the x, allows for tab to be <br>"
						+ "			deleted( Cannot be undone) <br>"
						+ "		add tab: <br>"
						+ "			click: creates a new tab<br>"
						+ "	Text Editor	<br>"
						+ "		click: selects editor, once selected you can move and re-size <br>"
						+ "		the editor<br>"
						+ "		right click: edits text<br>"
						+ "	Divs<br>"
						+ "		click: selects div, once selected you can move and re-size <br>"
						+ "		the div<br>"
						+ "	Images<br>"
						+ "		click: selects image, once selected you can move and re-size the image <br><br>"
						+ "	Menu Bar<br>"
						+ "	click:selects the menu bar, once selected you can move the bar<br>"
						+ "	(currently sub menus are not supported by this tool) <br><br>"
						+ "	Forms (advanced tool) <br>"
						+ "		once you bring down the list of items to be added to the form,<br>"
						+ "		you drag from the menu, into the list of form elements to add<br>"
						+ "		an element to the form. Once this is done you will be prompted<br>"
						+ "		to give a title to the form item/ The major thing about this <br>"
						+ "		tool is that we DO NOT provide the PHP/Perl code to take<br>"
						+ "		the information added into these forms and place it anywhere,<br>"
						+ "		we leave that up to the discretion of the user to hire someone <br>"
						+ "		to write this code for them.<br><br>"
						+ "</pre>"
						+ "<p class=faq><br><br>Step 4. Putting it all together<br><br>"
						+

						"Now that you have information on all the pieces of the puzzle required for "
						+ "ultimate website creation prowess, the next step is to take all the "
						+ "information here and apply it to creating your first website. We suggest "
						+ "that you begin your journey by taking the following steps: "
						+

						"<br><br>First: Decide what the theme of your website is going to be, what "
						+ "color scheme are you going to use, what is your target audience, "
						+ "and so on. "
						+

						"<br><br>Second: Map out all the pages you are going to require for this site: Ex. "
						+ "Home, Contact, Blog, Photos, etc. "
						+

						"<br><br>Third: Create your master template. This is important because the feel "
						+ "that you create here will translate to all of your other pages. It is "
						+ "important to add the menu bar here and any Text that you want to "
						+ "translate onto every other page, common places for this kind of text are "
						+ "in the footer and header. "
						+

						"<br><br>Fourth: Begin adding content and customizing the individual pages. "
						+ "In this step you will be adding images, text, forms, and other miscellany "
						+ "that will be constrained only to individual pages."
						+

						"<br><br>Fifth: Publish your page! Take the code that was generated and add "
						+ "into the public_html folder that is provided to you by your web-hosting "
						+ "company and Voila! You have created your very own website in "
						+ "just 5 simple steps.");

				body.addChild(content);
				frontPageCanvas.redraw();
			}

		});

		/************************************
		 * Forums section * we may or may not get to this :/ *
		 ************************************/
		menu.addItem("Forums", new Command() {

			@Override
			public void execute() {
				Canvas[] cs = body.getChildren();
				body.removeChild(cs[0]);

				HTML content = new HTML();
				content.setWidth("100%");
				content.setHeight("100%");
				frontPageCanvas.setHeight100();
				content.setStyleName("content-style");
				content.setHTML("<h2> Forums </h2> <br> Coming soon... ");
				body.addChild(content);
				frontPageCanvas.redraw();
			}

		});

		/************************
		 * The about us section *
		 ************************/
		menu.addItem("About us", new Command() {

			@Override
			public void execute() {
				Canvas[] cs = body.getChildren();
				body.removeChild(cs[0]);
				HTML content = new HTML();
				content.setWidth("100%");
				content.setHeight("100%");
				body.setWidth("60%");
				body.setLeft("20%");
				frontPageCanvas.setHeight100();
				content.setStyleName("content-style");
				content.setHTML("<h2> About us </h2> <br>"
						+ "<p class=faq>This product was developed and produced by a group of computer science students from UNM as a group project in CS 460: Software Engineering"
						+ ". This project, under the direction of professor Ackley has brought this product to fruition in the state that it can currently be "
						+ "seen here. As you can tell, this is still a work in progress, and as time progresses, we will continue to bring polish and new features to this product"
						+ " to further enhance its abilities. If you have any questions or would like to contact any of us, please visit the contact page for more information.<br"
						+ "<br> Thank you for your interest in WebCreate!. </p>");
				body.addChild(content);
				frontPageCanvas.redraw();
			}

		});

		/*****************
		 * Contact stuff *
		 *****************/
		menu.addItem("Contact", new Command() {

			@Override
			public void execute() {
				Canvas[] cs = body.getChildren();
				body.removeChild(cs[0]);

				HTML content = new HTML();
				content.setWidth("100%");
				content.setHeight("100%");
				body.setWidth("60%");
				body.setLeft("20%");
				frontPageCanvas.setHeight100();
				content.setStyleName("content-style");
				content.setHTML("<h2><center> Contact </h2> <br>Projct Developers:<br> "
						+ "<br>Nialls Chavez:       niallsc(at)gmail(dot)com"
						+ "<br>Theodore Schnepper:  theayiga(at)gmail(dot)com"
						+ "<br>Alexandre Rogozine:  alexandre.rogozine(at)live(dot)com"
						+ "<br>Ian Mallett:         imallett(at)cs(dot)unm(dot)edu"
						+ "<br>John Schulz:         jdschulz(at)unm(dot)edu");
				body.addChild(content);
				frontPageCanvas.redraw();
			}

		});

		/***************
		 * Login stuff *
		 ***************/
		menu.addItem("Log in", new Command() {

			@Override
			public void execute() {
				loginBox.setStyleName("login-style");
				loginBox.setTop("4%");
				loginBox.setLeft("60%");
				// loginBox.setBorder("1px solid");
				loginBox.setVisible(true);
				frontPageCanvas.addChild(loginBox);
			}
		});

		/*****************
		 * Sign up stuff *
		 *****************/
		menu.addItem("Sign up", new Command() {

			@Override
			public void execute() {

				Canvas[] cs = body.getChildren();
				body.removeChild(cs[0]);
				Canvas formCanvas = new Canvas();
				frontPageCanvas.setHeight("100%");

				formCanvas.setLeft("20%");
				formCanvas.setWidth100();
				body.setWidth("60%");
				body.setHeight("80%");
				body.setLeft("20%");
				body.setHeight100();

				TextBox firstName = new TextBox();
				firstName.setStyleName("input");
				TextBox lastName = new TextBox();
				lastName.setStyleName("input");
				TextBox email = new TextBox();
				email.setStyleName("input");
				PasswordTextBox pWord1 = new PasswordTextBox();
				pWord1.setStyleName("input");
				PasswordTextBox pWord2 = new PasswordTextBox();
				pWord2.setStyleName("input");

				TextBox phNum = new TextBox();
				phNum.setStyleName("input");

				// Image captchaImage = new
				// Image("http://127.0.0.1:8888/SimpleCaptcha.jpg");

				// TextBox captchaBox = new TextBox();

				Button submit = new Button();

				submit.setText("Submit");

				// NewUserHandler hand= new NewUserHandler(email, firstName,
				// lastName, phNum, pWord1,pWord2, captchaBox, frontPageCanvas,
				// cont);
				NewUserHandler hand = new NewUserHandler(email, firstName, lastName, phNum, pWord1, pWord2, frontPageCanvas, submit, cont);

				submit.addClickHandler(hand);

				VStack stack = new VStack();
				stack.setWidth("300px");
				stack.setTop("20%");
				stack.setLeft("25%");

				HStack hstack;
				Canvas label;

				label = new Canvas();
				label.setStyleName("newUser-style");
				label.setContents("First Name");

				hstack = new HStack();
				hstack.setHeight(25);
				hstack.addMember(label);
				hstack.addMember(firstName);

				stack.addMember(hstack);

				label = new Canvas();
				label.setStyleName("newUser-style");
				label.setContents("Last Name");

				hstack = new HStack();
				hstack.setHeight(25);
				hstack.addMember(label);
				hstack.addMember(lastName);

				stack.addMember(hstack);

				label = new Canvas();
				label.setStyleName("newUser-style");
				label.setContents("Phone Number");

				hstack = new HStack();
				hstack.setHeight(25);
				hstack.addMember(label);
				hstack.addMember(phNum);

				stack.addMember(hstack);

				label = new Canvas();
				label.setStyleName("newUser-style");
				label.setContents("Email");

				hstack = new HStack();
				hstack.setHeight(25);
				hstack.addMember(label);
				hstack.addMember(email);

				stack.addMember(hstack);

				label = new Canvas();
				label.setStyleName("newUser-style");
				label.setContents("Password");

				hstack = new HStack();
				hstack.setHeight(25);
				hstack.addMember(label);
				hstack.addMember(pWord1);

				stack.addMember(hstack);

				label = new Canvas();
				label.setStyleName("newUser-style");
				label.setContents("Re-Enter Password");

				hstack = new HStack();
				hstack.setHeight(25);
				hstack.addMember(label);
				hstack.addMember(pWord2);

				stack.addMember(hstack);

				stack.addMember(submit);

				body.addChild(stack);
				frontPageCanvas.addChild(body);
				frontPageCanvas.redraw();
			}

		});
		frontPageCanvas.addChild(menu);
	}

	public PrintCanvas getCanvas() {
		return frontPageCanvas;
	}
}
