package com.aexp.qa.google.constants.elements;

import org.openqa.selenium.By;

/**
 * Elements used to identify elements in Steps.
 */
public final class WebElements {

    /********************
     * LIST ALL Elements. *
     ********************/

    /**
     * FRAMES
     */

    /** Search phone call frame. **/
    public static final By FRAME_NAME_EXAMPLE = By.id("cpmInteractionDivFrame");
    /** Search phone call frame. **/
    public static final By Web_FRAME_SEARCH_ACCOUNT_NUMBER = By.id("cpmInteractionDivFrame");

    /** Left menu frame frame. **/
    public static final By Web_FRAME_LEFT_MENU = By.id("cpmInteractionDivFrame");

    /** Payment Management Tab Frame. **/
    public static final By Web_FRAME_PAYMENT_MGMT = By.id("cpmTabbedNavigation-DIVFrame");

    /** Auth Notes Tab Frame. **/
    public static final By Web_FRAME_AUTH_NOTES = By.id("cpmTabbedNavigation-DIVFrame");

    /** Customer Information Tab Frame. **/
    public static final By Web_FRAME_ACTIVE_TAB = By.id("cpmTabbedNavigation-DIVFrame");

    /** Account information Tab Frame. **/
    public static final By Web_FRAME_ACCOUNT_INFORMATION =
            By.cssSelector("iframe[src='/Web71/PRServlet/lcbIo3_AVrKG0xMl_NUhM8G0HmvqqAWR*/!pyNS_CPMPortal2_CPMWorkThread?pyActivity=%40base"
                    + "class.doUIAction&action=display&harnessName=AccountOverview&className=PegaCA-Portal&CPMAction=ShowCompositeTab']");
    /**
     * LINKS
     */

    /** list of links elements here. **/
    /** Phone call link. **/
//  public static final By Web_LINK_PHONE_CALL = By.xpath(".//*[@id='yui-gen0']/div[5]/ul/li/a");
  public static final By Web_LINK_PHONE_CALL = By.xpath("//*[@id='ItemMiddle'][text()='Phone Call']");


  /** New option for a new phone call. **/
//  public static final By Web_LINK_NEW_PHONE_CALL = By.cssSelector("span.menu-item-title-wrap");
  public static final By Web_LINK_NEW_PHONE_CALL = By.xpath("//*[@id='ItemMiddle']");

  /** Add Bank Hyperlink Payments subtab. **/
  public static final By Web_LINK_ADD_BANK = By.xpath("//*[@id='RULE_KEY']/table[2]/tbody/tr/td[2]/nobr/span/a");

  /** ECCO link in Auth Notes tab. **/
  public static final By Web_LINK_ECCO_AUTH_NOTES = By.xpath("//img[@id='ECCOIcon']");
  /**
   * LINK - MENU LINKS
   */

  /** Link General. **/
  public static final By Web_LINK_GENERAL = By.xpath("//div[@id='CAIntentTasks0AccordianHeader']/table/tbody/tr/td[2]");

  /** Link Payment. **/
  public static final By Web_LINK_PAYMENTS = By.xpath("//div[@id='CAIntentTasks1AccordianHeader']/table/tbody/tr/td[2]");

  /** Link Statement. **/
  public static final By Web_LINK_STATEMENT = By.xpath("//div[@id='CAIntentTasks2AccordianHeader']/table/tbody/tr/td[2]");

  /** Link Card Feature. **/
  public static final By Web_LINK_CARD_FEATURE = By.xpath("//div[@id='CAIntentTasks3AccordianHeader']/table/tbody/tr/td[2]");

  /** Link Customer Details. **/
  public static final By Web_LINK_CUSTOMER_DETAILS =By.xpath("//div[@id='CAIntentTasks4AccordianHeader']/table/tbody/tr/td[2]");

  /** GENERAL **/

  /** Action field in the left menu bar for sub tab Auth Notes. **/
  public static final By Web_SUB_LINK_AUTH_NOTES =
          By.xpath("//div[@id='CAIntentTasks0AccordianContent']/div[2]/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]");

  /** PAYMENTS **/

  /** Action field in the left menu bar for sub tab manage payments. **/
  public static final By Web_SUB_LINK_MANAGE_PAYMENT_METHOD = By.xpath("//div[@id='CAIntentTasks1_2']/table/tbody/tr/td[2]/div/table/tbody/tr/td");

  /** CUSTOMER DETAILS **/

  /** Action field in the left menu bar for sub tab manage customer details. **/
  public static final By Web_SUB_LINK_CUSTOMER_INFORMATION = By.xpath ("//div[@id='CAIntentTasks4_2']/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]");
  
  /**
   * TABS - MENU LINKS
   */
  /** Tab Payment. **/
  public static final By Web_TAB_PAYMENT = By.xpath("//div[@id='CAIntentTasks3AccordianHeader']/table/tbody/tr/td[2]");

  /** Action field in the left menu bar for sub tab manage payments. **/
  public static final By Web_SUB_TAB_MANAGE_PAYMENT_METHOD =
          By.xpath("//div[@id='CAIntentTasks3AccordianContent']/div/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]");

  /** Action field in the left menu bar for sub tab Auth Notes. **/
  public static final By Web_SUB_TAB_AUTH_NOTES =
          By.xpath("//div[@id='CAIntentTasks0AccordianContent']/div[2]/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]");
  
  
  

    /**
     * BUTTONS
     */

  /** Login button. **/
  public static final By Web_BUTTON_LOGIN = By.xpath("//*[@id='Login']");

  /** LogOut button. **/
  public static final By Web_BUTTON_LOGOUT = By.xpath("//span[@onclick=\"pega.cpm.menuActions.cpmLogOff('true','Are you sure you want to exit?','Press OK to continue, or Cancel to stay on the current page')\"]");

  /** Search phone call button. **/
  public static final By Web_BUTTON_SEARCH_ACCOUNT_NUMBER = By.xpath("//*[@id='RULE_KEY']/table/tbody/tr/td[8]/img");

  /** Bank Process Button. **/
  public static final By Web_BUTTON_BANK_ACCT_PROCEED = By.xpath("//*[@id='RULE_KEY']/table[2]/tbody/tr[1]/td[5]/nobr/span/button");

  /** Proceed Button. **/
  public static final By Web_BUTTON_PROCEED_MODIFY = By.xpath("//*[@id='RULE_KEY']/table/tbody/tr[1]/td[4]/nobr/span/button");

  /** Close Button Auth Notes. **/
  public static final By Web_BUTTON_CLOSE_AUTH_NOTES = By.xpath("//div[@id='pyFlowActionHTML']/div/table[2]/tbody/tr/td[2]/nobr/span/button");

  /** Submit Button Auth Notes. **/
  public static final By Web_BUTTON_SUBMIT_AUTH_NOTES = By.xpath("//div[@id='pyFlowActionHTML']/div/table[2]/tbody/tr/td[3]/nobr/span/button");

  /** End Call Button. **/
  public static final By Web_BUTTON_END_CALL = By.xpath("//div[@id='HARNESS_CONTENT']/table[2]/tbody/tr/td/div/div/span/div/table/tbody/tr/td[3]/div/table/tbody/tr/td[5]/nobr/table/tbody/tr/td[2]");

  /** Validate Card Authorization. **/
  public static final By Web_BUTTON_VALIDATE_PASSCODE = By.xpath("//*[@id='RULE_KEY']/table/tbody/tr[2]/td[3]/nobr/span/button");

  /** Validate Card Authorization. **/
  public static final By Web_BUTTON_VALIDATE_PHONE = By.xpath("//*[@id='RULE_KEY']/table/tbody/tr[4]/td[3]/nobr/span/button");


    /**
     * FIELDS to fill
     */

    /** Username field at Web login page. **/
    public static final By Web_FIELD_LOGIN_PAGE_USERNAME = By.xpath("//*[@id='textboxuid_AD']");

    /** Password field at Web login page. **/
    public static final By Web_FIELD_LOGIN_PAGE_PASSWORD = By.xpath("//*[@id='textboxpwd_AD']");

    /** Search account number field. **/
    public static final By Web_FIELD_SEARCH_ACCOUNT_NUMBER = By.xpath(".//*[@id='AccountNumber']");

    /** Bank Routing Number. **/
    public static final By Web_FIELD_BANK_ROUTING_NUMBER = By.xpath("//*[@id='BankRoutingNum']");

    /** Bank Account number. **/
    public static final By Web_FIELD_BANK_ACCT_NUMBER = By.xpath("//*[@id='BankAccountNum']");

    /** Confirm Bank Account number. **/
    public static final By Web_FIELD_BANK_ACCT_NO_CONF = By.xpath("//*[@id='BankAccountNumToVerify']");

    /** Dropdown Delete Action. **/
    public static final By Web_FIELD_DELETE_DROPDOWN =
            By.xpath("//div[@id='PEGA_GRID_CONTENT']/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[4]/div/div/div/div/div[4]"
    + "/div/div/span/div/table/tbody/tr[2]/td[1]/nobr/select");

    /** Dropdown Edit Action. **/
    public static final By Web_FIELD_EDIT_DROPDOWN =
            By.xpath("//div[@id='PEGA_GRID_CONTENT']/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[4]/div/div/div/div/div[4]"
    + "/div/div/span/div/table/tbody/tr[2]/td[1]/nobr/select");

    /** Passcode field in Cardmember Authentication. **/
    public static final By Web_FIELD_AUTH_PASSCODE = By.xpath("//*[@id='Answer']");


    /**
     * RADIO to select
     */

    /** Type of Add bank. **/
    public static final By Web_RADIO_ADD_BANK_ACCT = By.xpath("//*[@id='SelectBankRadioNewBank_rdi_1']");

    /** Type of Bank Account. **/
    public static final By Web_RADIO_ACCT_TYPE_CHECKINGS = By.xpath("//*[@id='BMBankAccountTypeChecking_rdi_1']");

    /** Personal or Business Account. **/
    public static final By Web_RADIO_ACCT_OWNER_BUSINESS = By.xpath("//*[@id='AccountPersonalOrBizBusiness_rdi_1']");

    /** First Card Number. **/
    public static final By Web_RADIO_CARD = By.xpath("//div[@id='PEGA_GRID_CONTENT']/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[1]/div/input[2]");

    /** Fourth Bank Account Number. **/
    public static final By Web_RADIO_BANK_ACCT =
            By.xpath("//div[@id='PEGA_GRID_CONTENT']/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[3]/div/span/div/div/div/div/div[4]"
    + "/div/div/table/tbody/tr[2]/td[1]/input[2]");

    /** Add Travel Status in Auth Notes. **/
    public static final By Web_RADIO_AUTH_ADD_TRAVEL_STATUS = By.xpath("//*[@id='TravelStatusAddorRemoveAdd Travel Status']");

    /** Remove Travel Status in Auth Notes. **/
    public static final By Web_RADIO_AUTH_REMOVE_TRAVEL_STATUS = By.xpath("//*[@id='TravelStatusAddorRemoveRemove Travel Status']");

    /** Validate Phone Number. **/
    public static final By Web_RADIO_PHONE_NUMBER = By.xpath("//*[@id='$PpyWorkPage$pCurrentUCIDQuestion$pUcidValResultsCorrect']");
    /**
     * Text on the WebPages
     */

    /** Header of Tab. **/
    public static final By Web_TEXT_AUTH_NOTES_HEADER = By.xpath("//*[@id='RULE_KEY']/table/tbody/tr/td[1]/nobr");

    /** Body of Tab. **/
    public static final By Web_TEXT_AUTH_NOTES_BODY = By.xpath("//*[@id='RULE_KEY']/div/div/div/div[2]/div");

    /** Auth Notes Card. **/
    public static final By Web_TEXT_AUTH_NOTES_CARD = By.xpath("//*[@id='RULE_KEY']/table/tbody/tr[2]/td[3]/nobr");

    /** Travel Card Status. **/
    public static final By Web_TEXT_AUTH_NOTES_TRAVEL_STATUS = By.xpath("//div[@id='pyFlowActionHTML']/div/span[2]/div/table/tbody/tr[1]/td[2]/nobr");

    /** Element reflects the text when the account is not loaded due time out. **/
    public static final By Web_TEXT_ACCOUNT_SEARCH_TIME_OUT = By.xpath(".//*[@id='PegaRULESErrorFlag']");
    /**
     * IMAGES
     */

    /** Card image in Auth Notes. **/
    public static final By Web_IMAGE_CARD = By.id("CardArtLogo");

    /** Ecco link image in Auth Notes. **/
    public static final By Web_IMAGE_ECCO_LINK = By.id("ECCOIcon");

    /** Ecco link image in Demographics. **/
    public static final By Web_IMAGE_ECCO_LINK_DEMOGRAPHICS = By.cssSelector(".icons>img");

    /**
     * Prevents from constructing objects of this class, by declaring this private constructor.
     */
    private WebElements() {
    }
}
