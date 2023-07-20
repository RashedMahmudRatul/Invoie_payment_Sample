Feature: Partner Invoice Payment

  @tts
  Scenario: Check invoice URL (US)
    Given a valid invoice url for "US" users
    When user clicks on the invoice url
    Then an invoice should appear necessary information
#  @tts
  Scenario: Check available payment method invoice payment(US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    Then card and crypto should be available for payment

#  @tts
  Scenario: Check available Crypto currencies for invoice payment(US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    Then accepted crypto currency list should appear

#  @tts
  Scenario: Check invoice amount conversion as per crypto currency(US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    Then converted amount should be change as per selected currency

#  @tts
  Scenario: Check Terms and Conditions redirection links for US users(Crypto)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    And user clicks on the links on Terms and Conditions
    Then user should see coinX terms page in a new tab

#  @tts
  Scenario: Check Privacy Policy redirection links for US users(Crypto)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    And user clicks on the links on Privacy Policy
    Then user should see us privacy page in a new tab

#  @tts
#  Scenario: Check invoice payment via BTC(US)
#    Given a valid invoice for US users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects BTC currency
#    And clicks on agreement checkbox and clicks on CONFIRM button
#    Then map3 should appear

#    @tts
  Scenario: Check Terms and Conditions redirection links for US users(Card)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects card as payment method
    And user clicks on the links on Terms and Conditions
    Then user should see coinX terms page in a new tab

#    @tts
  Scenario: Check Membership Agreement redirection links for US users(Card)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects card as payment method
    And user clicks on the links on Membership Agreement
    Then user should see us membership agreement page in a new tab

#  @tts
  Scenario: Check invoice payment via Card(US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects card as payment method
    And clicks on agreement checkbox and clicks on CONFIRM button
    And enter card details in Stripe and clicks on pay
    Then a payment success message should appear
  @tts
  Scenario: Check a already paid invoice link(US)
    When  user clicks on a US invoice link that has already been paid
    Then user should see an already paid error message

#  Scenario: Check an expired invoice link(US)
#    Given a valid US invoice link that has been expired
#    When user clicks on the invoice link
#    Then user should see an expired error message
#
#  Scenario: Check minimum amount for crypto currency(US)
#    Given a invoice url of amount '10' USD for US users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects different crypto currency
#    Then an error message with minimum crypto amount should appear
#
#  Scenario: Check maximum amount for crypto currency(US)
#    Given a invoice url of amount '200' USD for US users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects different crypto currency
#    Then an error message with maximum crypto amount should appear
#
#  Scenario: Check invoice payment via invalid Card(US)
#    Given a invoice url of amount '100' USD for US users
#    When user expends payment method dropdown
#    And user selects card as payment method
#    And enter invalid card details in Stripe and clicks on pay
#    Then a payment error message should appear
#
##    +++++++++++++++++++++++++++++++++++++++++++++++NON-US+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
#  @tts
#  Scenario: Check invoice URL(Non-us)
#    Given a valid invoice url for "BD" users
#    When user clicks on the invoice url
#    Then an invoice should appear necessary information

#  Scenario: Check available Crypto currencies for invoice payment(Non-us)
#    Given a valid invoice url for Non-US users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    Then accepted crypto currency list should appear
#
#  Scenario: Check invoice amount conversion as per crypto currency(Non-us)
#    Given a valid invoice url for Non-us users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects different crypto currency
#    Then converted amount as per currency should appear
#
#  Scenario: Check T&C and Privacy Policy redirection links for Non-US users
#    Given a valid invoice url for Non-us users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user clicks on the links on Terms and Privacy Policy
#    Then user should redirect to Nvayo terms and non-us policy page
#
#  Scenario: Check invoice payment via BTC(Non-us)
#    Given a valid invoice url for Non-us users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects BTC currency
#    And clicks on agreement checkbox and clicks on CONFIRM button
#    Then map3 should appear
#
#  Scenario: Check invoice payment via Card(Non-us)
#    Given a valid invoice url for Non-us users
#    When user expends payment method dropdown
#    And user selects card as payment method
#    And enter card details in Stripe and clicks on pay
#    Then a payment success message should appear
#
#  Scenario: Check a already paid invoice link(Non-us)
#    Given a invoice link that has already been paid
#    When user clicks on the invoice link
#    Then user should see an already paid error message
#
#  Scenario: Check an expired invoice link(Non-us)
#    Given a valid invoice link that has been expired
#    When user clicks on the invoice link
#    Then user should see an expired error message
#
#  Scenario: Check minimum amount for crypto currency(Non-us)
#    Given a invoice url of amount '10' USD for Non-us users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects different crypto currency
#    Then an error message with minimum crypto amount should appear
#
#  Scenario: Check maximum amount for crypto currency(Non-us)
#    Given a invoice url of amount '200' USD for Non-us users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects different crypto currency
#    Then an error message with maximum crypto amount should appear
#
#  Scenario: Check invoice payment via invalid Card(Non-us)
#    Given a invoice url of amount '100' USD for Non-us users
#    When user expends payment method dropdown
#    And user selects card as payment method
#    And enter invalid card details in Stripe and clicks on pay
#    Then a payment error message should appear