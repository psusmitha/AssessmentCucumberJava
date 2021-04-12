#Author: Susmitha Palli

@tag
Feature: Verifying page response, console errors and links in given page
  

  @ConsoleErrorValidation
  Scenario Outline: Verifying Console errors in given pages
    	When user launch the "<browsername>" browser
    	Then I verify console errors in "<url>" page
    	When user closes the application
    	Examples:
    		|browsername| url |
    		|chrome| https://www.w3.org/standards/badpage |
    		|chrome| https://www.w3.org/standards/webofdevices/multimodal|
    		|chrome| https://www.w3.org/standards/webdesign/htmlcss| 
    		
   @PageLinksValidations
  Scenario Outline: Verifying Links in given pages
    	When user launch the "<browsername>" browser
    	When user Navigate to "<url>"
    	When user capturing the links in the page
    	Then user verify the links Status
    	When user closes the application    	
    	Examples:
    		|browsername| url |
    		|chrome| https://www.w3.org/standards/badpage |
    		|chrome| https://www.w3.org/standards/webofdevices/multimodal|
    		|chrome| https://www.w3.org/standards/webdesign/htmlcss| 
    	
  @ResponseValidation
  Scenario Outline: Verifying page response from given pages
    #	When I navigate to "<url>" page
    	Then I verify page response from "<url>" page
    	
    	Examples:
    		| url |
    		| https://www.w3.org/standards/badpage |
    		| https://www.w3.org/standards/webofdevices/multimodal |
    		| https://www.w3.org/standards/webdesign/htmlcss |
