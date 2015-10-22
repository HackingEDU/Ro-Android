package co.hackingedu.ro.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Joseph on 9/18/15.
 * TODO: Checking for whether file exists or not
 * TODO: Online testing for putting and retrieving
 */
public class CacheManager {
    /**
     * Logging Tag
     */
    private final String TAG = "CacheManager";

    /**
     * Endpoints of app to use in get function
     * For usage in specifying JSON Files to update
     * from each respective endpoint
     */
    public final String FAQS_FILE = "/faqs",
            GENERAL_FILE = "/general",
            EVENTS_FILE = "/events",
            NOTIFS_FILE = "/notifications",
            MAPS_FILE = "/maps",
            PRIZES_FILE = "/prizes",
            SPONSORS_FILE = "/sponsors";

    //
    public String JSON_LONG_SCHEDULE_STRING = "[{\"_id\":\"56176ee0d22d6c9c7371ca5b\",\"name\":\"Getting Started With Twilio\",\"time\":\"10:00pm-10:15pm\",\"day\":\"friday\",\"location\":\"N Stage\",\"image\":[\"http://www.lohika.com/wp-content/uploads/2014/10/twilio-logo.png\"],\"about\":\"Build something cool using Twilio\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"10:00\",\"endTime\":\"10:15\",\"speaker\":[\"N/A\"],\"type\":\"workshop\"},{\"_id\":\"56176eeed22d6c9c7371ca5c\",\"name\":\"Hack with Chegg!\",\"time\":\"10:00pm-10:15pm\",\"day\":\"friday\",\"location\":\"Main Stage\",\"image\":[\"https://a248.e.akamai.net/static.chegg.com/assets/site/logos/logo-chegg-fb-200x200.png\"],\"about\":\"Know more about how you can utilize some of the data points from Chegg in your awesome hack ideas.\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"10:00\",\"endTime\":\"10:15\",\"speaker\":[\"N/A\"],\"type\":\"workshop\"},{\"_id\":\"56176efcd22d6c9c7371ca5d\",\"name\":\"Make Your App Speak\",\"time\":\"10:20pm-10:35pm\",\"day\":\"friday\",\"location\":\"N Stage\",\"image\":[\"\"],\"about\":\"How to instantly embed collaboration into your app like messaging, file sharing, whiteboarding, screensharing, and video chat.\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"10:20\",\"endTime\":\"10:35\",\"speaker\":[\"N/A\"],\"type\":\"workshop\"},{\"_id\":\"56176ebcd22d6c9c7371ca59\",\"name\":\"Braintree API Demo\",\"time\":\"10:20pm-10:35pm\",\"day\":\"friday\",\"location\":\"S Stage\",\"image\":[\"http://accel-production.s3.amazonaws.com/accel/uploads/company-logo/796e363b005840fbb2b222c53484dc98/logo_0001_Braintree.jpg.200x100_q95_upscale.jpg\"],\"about\":\"\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"10:20\",\"endTime\":\"10:35\",\"speaker\":[\"N/A\"],\"type\":\"speaker\"},{\"_id\":\"5611bb7c087016d30036745e\",\"name\":\"Opening Keynote - CIO of Chegg\",\"time\":\"11:05-11:15pm\",\"day\":\"friday\",\"location\":\"Main Stage\",\"image\":[\"https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/1/000/02f/296/17ef42a.jpg\"],\"about\":\"\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"11:05\",\"endTime\":\"11:15\",\"speaker\":[\"Mike Osier\"],\"type\":\"speaker\"},{\"_id\":\"56176e9dd22d6c9c7371ca57\",\"name\":\"Team Building\",\"time\":\"11:30pm-12:00pm\",\"day\":\"friday\",\"location\":\"N & S Stage\",\"image\":[\"http://colleges.jazanu.edu.sa/sites/en/med/PublishingImages/large_icon_our_team.png\"],\"about\":\"\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"11:30\",\"endTime\":\"12:00\",\"speaker\":[\"N/A\"],\"type\":\"workshop\"},{\"_id\":\"5611b74e087016d300367456\",\"name\":\"Team Building - N Stage\",\"time\":\"6:00pm-9:00pm\",\"day\":\"friday\",\"location\":\"N-Stage\",\"image\":[\"https://s3-us-west-2.amazonaws.com/assets-cater2me/jobs.cater2.me/team-icon.png\"],\"about\":\"Startup weekend team building style. Line up to pitch your idea on stage. Teammate will then come find you after your pitch.\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"6:30\",\"endTime\":\"7:30\",\"speaker\":[\"N/A\"],\"type\":\"workshop\"},{\"_id\":\"5611b9c2087016d300367459\",\"name\":\"Hackathon Founders Panel\",\"time\":\"7:30-8:30pm\",\"day\":\"friday\",\"location\":\"N Stage\",\"image\":[\"https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAALBAAAAJDIxNWU4OGY5LWQwODgtNDYxMi04YTgwLTcwMzdmM2I2ZTA5Mw.jpg\"],\"about\":\"Founders will talk about some challenges in starting a hackathon and how to get past them.\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"7:30\",\"endTime\":\"8:30\",\"speaker\":[\"Hackathon Founders\",\"Dave Fontenot\",\"Founder of Pearl Hacks\",\"Founder of CalHacks\"],\"type\":\"speaker\"},{\"_id\":\"5611b7d9087016d300367457\",\"name\":\"Fireside Chat: Sam Altman, President of Y Combinator\",\"time\":\"7:30pm-8:00pm\",\"day\":\"friday\",\"location\":\"Main Stage\",\"image\":[\"http://static6.businessinsider.com/image/4d0a8c8accd1d5c42d250000-480/sam-altman.jpg\"],\"about\":\"Come hear an insightful Fireside Chat, from one of Silicon Valley's smartest minds, Sam Altman.\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"7:30\",\"endTime\":\"8:30\",\"speaker\":[\"Sam Altman\"],\"type\":\"speaker\"},{\"_id\":\"5611bcd2087016d30036745f\",\"name\":\"Engineering Superpowers: Vivienne Ming - Founder of Socos\",\"time\":\"8:30-9:00pm\",\"day\":\"friday\",\"location\":\"S Stage\",\"image\":[\"https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAL7AAAAJGNjNWE3ZTM1LTJjZWEtNGUyMS05Y2RiLTA5ZmYyNzNlNDA0NA.jpg\"],\"about\":\"Turning Cool Science into Products that Change the World\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"8:30\",\"endTime\":\"9:00\",\"speaker\":[\"Vivienne Ming\"],\"type\":\"speaker\"},{\"_id\":\"5611ba6b087016d30036745a\",\"name\":\"API Demos\",\"time\":\"9:00-10:35pm\",\"day\":\"friday\",\"location\":\"Main Stage\",\"image\":[\"http://semiaccurate.com/assets/uploads/2011/04/IBM-Intel-logo.jpg\"],\"about\":\"Sponsors will have 15 Minute demos of their APIs & Platforms\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"9:00\",\"endTime\":\"10:35\",\"speaker\":[\"N/A\"],\"type\":\"speaker\"},{\"_id\":\"56176ed2d22d6c9c7371ca5a\",\"name\":\"Clusterpoint Cloud\",\"time\":\"9:40pm-9:55pm\",\"day\":\"friday\",\"location\":\"N Stage\",\"image\":[\"https://d1qb2nb5cznatu.cloudfront.net/startups/i/53410-0bf64e62980c6737effb17291a750105-medium_jpg.jpg?buster=1438957298\"],\"about\":\"Sign-up for a Clusterpoint Cloud account!\",\"eventDay\":23,\"timePeriod\":\"pm\",\"startTime\":\"9:40\",\"endTime\":\"9:55\",\"speaker\":[\"N/A\"],\"type\":\"workshop\"},{\"_id\":\"562709d5e4b0bab43f8a9590\",\"name\":\"CIFilter Animations and the New 3D Touch\",\"time\":\"10:00am-10:45am\",\"day\":\"saturday\",\"location\":\"N Stage\",\"image\":[\"\"],\"about\":\"iOS Workshop\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"10:00\",\"endTime\":\"10:45\",\"speaker\":[\"\"],\"type\":\"workshop\"},{\"_id\":\"56270a2ce4b0bab43f8a9593\",\"name\":\"Disrupting Education: IBM Watson on Bluemix\",\"time\":\"10:00am-10:45am\",\"day\":\"saturday\",\"location\":\"S Stage\",\"image\":[\"\"],\"about\":\"\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"10:00\",\"endTime\":\"10:45\",\"speaker\":[\"\"],\"type\":\"workshop\"},{\"_id\":\"56270ae3e4b0bab43f8a9598\",\"name\":\"Morning Keynote - Jack Herrick, CEO WikiHow\",\"time\":\"11:00am-11:30am\",\"day\":\"saturday\",\"location\":\"Main Stage\",\"image\":[\"\"],\"about\":\"Speaker.\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"11:00\",\"endTime\":\"11:30\",\"speaker\":[\"Jack Herrick\"],\"type\":\"speaker\"},{\"_id\":\"56270d40e4b0bab43f8a95a0\",\"name\":\"React.js & Websockets using Twilio\",\"time\":\"11:30am-12:00am\",\"day\":\"saturday\",\"location\":\"S Stage\",\"image\":[\"\"],\"about\":\"React.js & Websockets using Twilio\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"11:30\",\"endTime\":\"12:00\",\"speaker\":[\"Twilio\"],\"type\":\"workshop\"},{\"_id\":\"56270ca1e4b0bab43f8a959e\",\"name\":\"Dolby audio for Android apps and HTML 5\",\"time\":\"11:30am-12:00pm\",\"day\":\"saturday\",\"location\":\"N Stage\",\"image\":[\"\"],\"about\":\"How to utilize Dolby audio for Android apps and HTML 5\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"11:30\",\"endTime\":\"12:00\",\"speaker\":[\"Dolby\"],\"type\":\"workshop\"},{\"_id\":\"5627001fe4b0bab43f8a9511\",\"name\":\"Intro Workshops\",\"time\":\"12:00 am\",\"day\":\"saturday\",\"location\":\"N & S Stages\",\"image\":[\"\"],\"about\":\"Intro to iOS, HTML/CSS, Python and Rapid Prototyping with Flexbox\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"12:00\",\"endTime\":\"1:45\",\"speaker\":[\"iOS\",\"HTML/CSS\",\"Flexbox\",\"Kamesh\"],\"type\":\"workshop\"},{\"_id\":\"5626fafbe4b0bab43f8a94db\",\"name\":\"Napathon Hall Opens\",\"time\":\"12:00 am\",\"day\":\"saturday\",\"location\":\"Naphaton Hall\",\"image\":[\"\"],\"about\":\"Napathon Hall is a great place to have some good sleep\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"12:00\",\"endTime\":\"\",\"speaker\":[\"Napathon Hall\"],\"type\":\"none\"},{\"_id\":\"5626fa45e4b0bab43f8a94c8\",\"name\":\"Midnight Snack\",\"time\":\"12:00 am\",\"day\":\"saturday\",\"location\":\"West & East Food Areas\",\"image\":[\"\"],\"about\":\"Midnight snacks\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"12:00\",\"endTime\":\"\",\"speaker\":[\"Midnight Snacks\"],\"type\":\"food\"},{\"_id\":\"5611bb0b087016d30036745d\",\"name\":\"HACKING COMMENCES\",\"time\":\"12:00 am\",\"day\":\"saturday\",\"location\":\"San Mateo Event Center\",\"image\":[\"https://cdn2.iconfinder.com/data/icons/metro-uinvert-dock/256/Coding_App.png\"],\"about\":\"\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"12:00\",\"endTime\":\"\",\"speaker\":[\"Hacking Commences\"],\"type\":\"important\"},{\"_id\":\"56270e99e4b0bab43f8a95cb\",\"name\":\"Panel: From Concept To Reality\",\"time\":\"1:00pm-2:00pm\",\"day\":\"saturday\",\"location\":\"N Stage\",\"image\":[\"\"],\"about\":\"Founder of Teespring, Founder of PersistIQ, Founder of Make School\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"9:15\",\"endTime\":\"9:45\",\"speaker\":[\"Founder of Teespring\",\"Founder of PersistIQ\",\"Founder of MakeSchool\"],\"type\":\"workshop\"},{\"_id\":\"56176eacd22d6c9c7371ca58\",\"name\":\"Breakfast\",\"time\":\"8:00 - 10:00 am\",\"day\":\"saturday\",\"location\":\"West & East Food Areas\",\"image\":[\"http://icons.iconarchive.com/icons/icons8/windows-8/128/Programming-Java-Coffee-Cup-Logo-icon.png\"],\"about\":\"Hackers - start your engines!\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"8:00\",\"endTime\":\"10:00\",\"speaker\":[\"Breakfast\"],\"type\":\"food\"},{\"_id\":\"562705dee4b0bab43f8a9559\",\"name\":\"How to Set Up Your Own Server\",\"time\":\"9:15am-9:45am\",\"day\":\"saturday\",\"location\":\"N Stage\",\"image\":[\"\"],\"about\":\"Learn how to set up your own server\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"9:15\",\"endTime\":\"9:45\",\"speaker\":[\"Workshop Speaker\"],\"type\":\"workshop\"},{\"_id\":\"56270972e4b0bab43f8a958c\",\"name\":\"Teaching Through Play\",\"time\":\"9:15am-9:45am\",\"day\":\"saturday\",\"location\":\"S Stage\",\"image\":[\"\"],\"about\":\"We will explore the how gaming and entertainment can be used in an educational setting as video games are quickly becoming one of the most popular entertainment options for all ages. This trend can be harnessed to teach kids and adults in an enjoyable and productive way.\",\"eventDay\":24,\"timePeriod\":\"am\",\"startTime\":\"9:15\",\"endTime\":\"9:45\",\"speaker\":[\"ROBLOX\"],\"type\":\"workshop\"},{\"_id\":\"56270dd6e4b0bab43f8a95a4\",\"name\":\"Lunch\",\"time\":\"12:00pm-2:00pm\",\"day\":\"saturday\",\"location\":\"West & East Food Areas\",\"image\":[\"\"],\"about\":\"\",\"eventDay\":24,\"timePeriod\":\"pm\",\"startTime\":\"12:00\",\"endTime\":\"2:oo\",\"speaker\":[\"Lunch\"],\"type\":\"food\"},{\"_id\":\"56274af2e4b0bab43f8a98bb\",\"name\":\"HACKING ENDS\",\"time\":\"10:00am\",\"day\":\"sunday\",\"location\":\"N/A\",\"image\":[\"http://media4.popsugar-assets.com/files/2015/05/19/789/n/1922441/8376e0d8_edit_img_image_845258_1427288400_Bon-Voyage-Thumb.xlarge/i/Best-Travel-Hacks.jpg\"],\"about\":\"Laptops down, if you want to practice your pitch or work on your pitch deck, head on over to South Stage, North Stage, or Redwood Hall\",\"eventDay\":25,\"timePeriod\":\"am\",\"startTime\":\"10:00\",\"endTime\":\"10:00\",\"speaker\":[\"N/A\"],\"type\":\"important\"},{\"_id\":\"56274bb0e4b0bab43f8a98c4\",\"name\":\"Food Truck Lunch-a-Thon! Brought to you by Namecheap and .Club\",\"time\":\"11:00am-2:00pm\",\"day\":\"sunday\",\"location\":\"East Gate\",\"image\":[\"http://flat12.me/wp-content/uploads/2014/01/chef-dans-southern-comfort-food-truck.jpg\"],\"about\":\"6 awesome food trucks!\",\"eventDay\":25,\"timePeriod\":\"am\",\"startTime\":\"11:00\",\"endTime\":\"2:00\",\"speaker\":[\"N/A\"],\"type\":\"food\"},{\"_id\":\"5627497be4b0bab43f8a98aa\",\"name\":\"Breakfast\",\"time\":\"7:00am-9:00am\",\"day\":\"sunday\",\"location\":\"West and East Food Areas\",\"image\":[\"http://www.ineedmotivation.com/blog/wp-content/uploads/2008/03/breakfast.JPG\"],\"about\":\"\",\"eventDay\":25,\"timePeriod\":\"am\",\"startTime\":\"7:00\",\"endTime\":\"9:00\",\"speaker\":[\"N/A\"],\"type\":\"food\"},{\"_id\":\"56274a31e4b0bab43f8a98b0\",\"name\":\"ANNOUNCEMENT: Last Hour of Hacking\",\"time\":\"9:00am\",\"day\":\"sunday\",\"location\":\"Hardware Lab\",\"image\":[\"http://www.stgcr.com/wp-content/uploads/2013/01/hardware-2.png\"],\"about\":\"If you used hardware including ethernet to USB cables, you need to return those before you demo.\",\"eventDay\":25,\"timePeriod\":\"am\",\"startTime\":\"9:00\",\"endTime\":\"10:00\",\"speaker\":[\"N/A\"],\"type\":\"important\"},{\"_id\":\"56274c17e4b0bab43f8a98ca\",\"name\":\"Demos Round 1\",\"time\":\"1:00pm-1:45pm\",\"day\":\"sunday\",\"location\":\"Main Area\",\"image\":[\"http://twopointoakland.com/wp-content/uploads/2014/08/bluedemobutton.jpg\"],\"about\":\"First Round of Project Demos\",\"eventDay\":25,\"timePeriod\":\"pm\",\"startTime\":\"1:00\",\"endTime\":\"1:45\",\"speaker\":[\"N/A\"],\"type\":\"important\"},{\"_id\":\"56274c42e4b0bab43f8a98d1\",\"name\":\"Demos Round 2\",\"time\":\"2:00pm-2:45pm\",\"day\":\"sunday\",\"location\":\"Main Area\",\"image\":[\"http://twopointoakland.com/wp-content/uploads/2014/08/bluedemobutton.jpg\"],\"about\":\"Second Round of Project Demos\",\"eventDay\":25,\"timePeriod\":\"pm\",\"startTime\":\"2:00\",\"endTime\":\"2:45\",\"speaker\":[\"N/A\"],\"type\":\"important\"},{\"_id\":\"56274cb3e4b0bab43f8a98dd\",\"name\":\"HARDWARE RETURN\",\"time\":\"3:00pm-3:30pm\",\"day\":\"sunday\",\"location\":\"Hardware Lab\",\"image\":[\"http://www.signaturehardware.com/media/catalog/product/cache/1/image/680x/9df78eab33525d08d6e5fb8d27136e95/8/0/8060-l-egg-crate-wood-register.jpg\"],\"about\":\"\",\"eventDay\":25,\"timePeriod\":\"pm\",\"startTime\":\"3:00\",\"endTime\":\"3:30\",\"speaker\":[\"N/A\"],\"type\":\"important\"},{\"_id\":\"56274d1ae4b0bab43f8a98fa\",\"name\":\"Closing Ceremony\",\"time\":\"3:30pm-6:00pm\",\"day\":\"sunday\",\"location\":\"Main Stage\",\"image\":[\"http://a3.tnt.ms/assets/products/med/200843-Closing-Ceremony-08cbcba6e956a10d465d0d7c3ac734b2.png\"],\"about\":\"Final Judging, Awards, Closing Talks\",\"eventDay\":25,\"timePeriod\":\"pm\",\"startTime\":\"3:30\",\"endTime\":\"6:00\",\"speaker\":[\"N/A\"],\"type\":\"important\"},{\"_id\":\"56274d94e4b0bab43f8a9908\",\"name\":\"Closing Keynote: Tony Conrad - Founder/ CEO about.me & Sphere\",\"time\":\"4:30pm-5:00pm\",\"day\":\"sunday\",\"location\":\"Main Stage\",\"image\":[\"http://i-cms.journaldunet.com/image_cms/original/1571926-avec-about-me-vous-ne-laissez-pas-a-google-le-soin-de-definir-votre-identite-a-votre-place.jpg\"],\"about\":\"Tony Conrad - Founder/ CEO about.me & Sphere\",\"eventDay\":25,\"timePeriod\":\"pm\",\"startTime\":\"4:30\",\"endTime\":\"5:00\",\"speaker\":[\"Tony Conrad\"],\"type\":\"speaker\"},{\"_id\":\"56274e10e4b0bab43f8a990e\",\"name\":\"Shuttles Depart\",\"time\":\"6:30pm-8:30pm\",\"day\":\"sunday\",\"location\":\"East Gate\",\"image\":[\"http://www.formfonts.com/files/1/4772/ford-shuttlebus_FF_Model_ID4772_1_Ford_ShuttleBus.jpg\"],\"about\":\"\",\"eventDay\":25,\"timePeriod\":\"pm\",\"startTime\":\"6:30\",\"endTime\":\"8:30\",\"speaker\":[\"N/A\"],\"type\":\"important\"}] ";



    //LONG STRING FOR FAQ

    public String JSON_LONG_FAQ_STRING= "[{\"_id\":\"5603888a98a5e5af408bbac7\",\"q\":\"How big are teams?\",\"a\":\"To qualify for prizes, teams can be as big as 4 people. If you don’t have a team organized prior to our event, that's okay! We will have a team-forming session where you will meet tons of amazing people!\"},{\"_id\":\"5603888a98a5e5af408bbac8\",\"q\":\"Who can attend?\",\"a\":\"Anyone! We accept a wide range of applicants, from beginner programmers, to experienced hackathon veterans. We only require that 18 or under applicants bring a signed parental consent form.\"},{\"_id\":\"560281ca98a5e5af408bbac2\",\"q\":\"What is a hackathon?\",\"a\":\"A hackathon is a 24-36 hour programming competition where students from various backgrounds come together, form teams, share ideas, and help each other build innovative projects. It is one of the best ways to meet new people, learn from mentors, and make cool stuff.\"},{\"_id\":\"55f93d9498a5e5af408bbabe\",\"q\":\"Do you have a code of conduct?\",\"a\":\"Yes! Our code of conduct is at the bottom of our page and here. TL;DR please respect all others at the event. We reserve the right to remove those who don’t make our event comfortable for everyone.\"},{\"_id\":\"560281e498a5e5af408bbac3\",\"q\":\"What should I bring?\",\"a\":\"All attendees are required to bring a valid government ID (ex: driver’s license, passport) for admission. Also bring your laptop, and any hardware you wish to use in your hack. We will have sponsors loaning out tons of hardware as well!\"},{\"_id\":\"560281e498a5e5af408bbac4\",\"q\":\"I can't code. Help?\",\"a\":\"If you can’t code, don’t worry! Learning is a big part of the Hackathon experience! We will have programming workshops and lots of mentors to answer any questions that you might have. The best teams have a balance of technical, design and product talents.\"},{\"_id\":\"5627442efba31c474fc49ed3\",\"q\":\"How do I get there?\",\"a\":\"The event is at the San Mateo Event Center. We will have shuttles going to most major Universities in California. There will also be plenty of parking at the event ($10/day) and the Hayward Park Caltrain station is less than 15 minutes away.\"},{\"_id\":\"5627444efba31c474fc49ed4\",\"q\":\"How much does this cost?\",\"a\":\"FREE! The event, food and even the shuttles are all FREE! We are also doing travel reimbursements for both local and international travel on a case by case basis, budget depending. Priority will be given to earlier applicants, so apply now!\"},{\"_id\":\"562747f456d40f58369f7792\",\"q\":\"Are high school students allowed?\",\"a\":\"Yes! Please share the event with other students and teachers since we’ve found many offer extra credit for classes going as a group! If you are under 18, we do require your parent or guardian’s permission to attend and will contact you if you are accepted for more details.\"}]";
    /**
     * Backend Manager to handle API Calls and update Cached information
     */
    private BackendManager backendManager;

    /**
     * Global Shared Preferences to handle local storage manipulation within class
     */
    private SharedPreferences sharedPreferences;

    /**
     * Editor to Shared Preferences
     */
    private SharedPreferences.Editor editor;

    /**
     * String to designate access to our local storage
     */
    private static final String PREF_NAME = "HackingEDU_Data";

    /**
     *
     * @param _sharedPreferences
     */
    public CacheManager(SharedPreferences _sharedPreferences){
        backendManager = new BackendManager();
        sharedPreferences = _sharedPreferences;
        editor = sharedPreferences.edit();
    }

    /**
     * Constructor Method to update everything
     * Updates all local JSON files
     */
    public CacheManager(Context _context) throws IOException, JSONException {
        Log.i(TAG, "instantiating BackendManager");
        backendManager = new BackendManager();
        Log.i(TAG, "instantiating sharedPreferences");
        sharedPreferences = getSharedPreferences(_context);
        editor = sharedPreferences.edit();
        updateAllJSONFiles();
    }

    /**
     * Constructor Method with specified JSON File to update
     * @param _JsonFile specified JSON File to update
     * @param _context Android Activity context
     * @throws IOException thrown when issues with backendManager are faced
     * @throws JSONException thrown when JSON conversion issues are faced
     */
    public CacheManager(String _JsonFile, Context _context) throws IOException, JSONException {
        Log.i(TAG, "instantiating BackendManager");
        backendManager = new BackendManager();
        Log.i(TAG, "instantiating sharedPreferences");
        sharedPreferences = getSharedPreferences(_context);
        editor = sharedPreferences.edit();
        Log.i(TAG, "updating file");
        updateJsonFile(_JsonFile);
    }

    /**
     * Public void to poll server for updates on all JSON Files
     */
    public void updateAllJSONFiles() throws IOException, JSONException {
        updateJsonFile(NOTIFS_FILE);
        updateJsonFile(FAQS_FILE);
        updateJsonFile(GENERAL_FILE);
        updateJsonFile(EVENTS_FILE);
        updateJsonFile(MAPS_FILE);
        updateJsonFile(SPONSORS_FILE);
        updateJsonFile(PRIZES_FILE);
    }

    /**
     * Public void to poll server for updates on specified JSON File
     * @param JsonFile name of file to update
     */
    public void updateJsonFile(String JsonFile) throws IOException, JSONException {
        // utilize the backend manager to call the API
        Log.i(TAG, "File to update: " + JsonFile);
        String JsonToString = (backendManager.get(JsonFile)).toString();
        Log.i(TAG, "UpdateJsonFile JsonToString: " + JsonToString);
        // locally save the JSON String as a preference accessible by the context
        editor.putString(JsonFile, JsonToString);

        // Commit the edits!
        editor.commit();
    }

    /**
     * Static getter for shared Preferences
     * @param context TODO: Something I need to look more into on why context is necessary
     * @return stored information
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        //return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Public status checker method for the existence of a JsonFile
     * @param JsonFile File to check
     * @return whether getString succeeds
     */
    public boolean fileIsNull(String JsonFile){
        return (sharedPreferences.getString(JsonFile, null) == null);
    }

    /**
     * Public Getter method to retrieve local storage as String
     * @param JsonFile specified JSON file to retrieve
     * @param _context provided context from Android Activity
     * @return locally stored file contents as String
     */
    public String getJsonString(String JsonFile, Context _context){

        String str;

        if(JsonFile == EVENTS_FILE) {
            str = getSharedPreferences(_context).getString(JsonFile, JSON_LONG_SCHEDULE_STRING);
        }
        else if(JsonFile == FAQS_FILE){
            str = getSharedPreferences(_context).getString(JsonFile, JSON_LONG_FAQ_STRING);
        }
        else{
            str = getSharedPreferences(_context).getString(JsonFile, null);
        }
        Log.i(TAG, "getJsonString JSONString: " + str);
        return str;
    }

    /**
     * Public getter method to retrieve local storage as JSONArray
     * @param JsonFile specified JSON file to retrieve
     * @param _context provided context from Android Activity
     * @return locally stored file contents as JSONArray
     * @throws JSONException whenever there is an issue with String-JSON conversion
     */
    public JSONArray getJsonArray(String JsonFile, Context _context) throws JSONException {

        if(getJsonString(JsonFile, _context) == null){
            return null;
        }
        return new JSONArray(getJsonString(JsonFile, _context));
    }

    /**
     * Public Deleter method to delete a single JSON File
     * @param JsonFile specified JSON File to delete
     */
    public void removeJsonFile(String JsonFile){
        // delete key, and thus the data as well
        editor.remove(JsonFile);

        // Commit the edits!
        editor.commit();
    }

    /**
     * Public deleter method to delete all preference data
     */
    public void clearAll(){
        // delete key, and thus the data as well
        editor.clear();

        // Commit the edits!
        editor.commit();
    }
}
