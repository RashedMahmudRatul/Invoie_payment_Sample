Feature: Partner Invoice Payment

  @inv_us
  Scenario: Check invoice URL (US)
    Given a valid invoice url for "US" users
    When user clicks on the invoice url
    Then an invoice should appear necessary information

  @inv_us @crypto_us
  Scenario: Check available payment method invoice payment (US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    Then card and crypto should be available for payment

  @inv_us @crypto_us
  Scenario: Check available Crypto currencies for invoice payment (US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    Then accepted crypto currency list should appear

  @inv_us @crypto_us
  Scenario: Check invoice amount conversion as per crypto currency (US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    Then converted amount should be change as per selected currency

  @inv_us @crypto_us
  Scenario: Check Terms and Conditions redirection links for US users (Crypto)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    And user clicks on the links on Terms and Conditions
    Then user should see coinX terms page in a new tab

  @inv_us @crypto_us
  Scenario: Check Privacy Policy redirection links for US users (Crypto)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    And user clicks on the links on Privacy Policy
    Then user should see us privacy page in a new tab

#    crypto error in portal
#  @tts
#  Scenario: Check invoice payment via BTC(US)
#    Given a valid invoice for US users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects BTC currency
#    And clicks on agreement checkbox and clicks on CONFIRM button
#    Then map3 should appear

  @inv_us @card_us
  Scenario: Check Terms and Conditions redirection links for US users (Card)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects card as payment method
    And user clicks on the links on Terms and Conditions
    Then user should see coinX terms page in a new tab

  @inv_us @card_us
  Scenario: Check Membership Agreement redirection links for US users (Card)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects card as payment method
    And user clicks on the links on Membership Agreement
    Then user should see us membership agreement page in a new tab

  @inv_us @card_us
  Scenario: Check invoice payment via Card(US)
    Given a valid invoice for US users
    When user expends payment method dropdown
    And user selects card as payment method
    And clicks on agreement checkbox and clicks on CONFIRM button
    And enter card details in Stripe and clicks on pay
    Then a payment success message should appear

  @inv_us @card_us
  Scenario: Check a already paid invoice link(US)
    When  user clicks on a US invoice link that has already been paid
    Then user should see an already paid error message

  @inv_us
  Scenario: Check an expired invoice link(US)
    Given a "US" invoice link that has been expired
    When user clicks on the invoice url
    Then user should see an expired error message

  @inv_us @crypto_us
  Scenario: Check minimum amount for crypto currency (US)
    Given a invoice url of amount "10" USD for "US" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects crypto as payment method
    And user selects ETH crypto currency
    Then an error message with minimum crypto amount should appear

  @inv_us @crypto_us
  Scenario: Check maximum amount for ETH, LTC, USDT currency(US)
    Given a invoice url of amount "110000" USD for "US" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects crypto as payment method
    And user selects ETH,LTC,USDT crypto currency
    Then an error message with maximum crypto amount should appear

  @inv_us @crypto_us
  Scenario: Check maximum amount for BTC currency (US)
    Given a invoice url of amount "200000" USD for "US" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects crypto as payment method
    Then an error message for maximum BTC should appear

  @inv_us @card_us
  Scenario: Check invoice payment via invalid Card (US)
    Given  a valid invoice url for "US" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects card as payment method
    And clicks on agreement checkbox and clicks on CONFIRM button
    And enter invalid card details in Stripe and clicks on pay
    Then a payment error message should appear

##    +++++++++++++++++++++++++++++++++++++++++++++++NON-US+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @inv_non_us
  Scenario: Check invoice URL (Non-us)
    Given a valid invoice url for "BD" users
    When user clicks on the invoice url
    Then an invoice should appear necessary information

  @inv_non_us @crypto_non_us
  Scenario: Check available Crypto currencies for invoice payment (Non-us)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    Then accepted crypto currency list should appear

  @inv_non_us @crypto_non_us
  Scenario: Check invoice amount conversion as per crypto currency(Non-us)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    Then converted amount should be change as per selected currency

  @inv_non_us @crypto_non_us
  Scenario: Check Terms and Conditions redirection links for Non-US users(Crypto)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    And user clicks on the links on Terms and Conditions
    Then user should see terms page in a new tab

  @inv_non_us @crypto_non_us
  Scenario: Check Privacy Policy redirection links for Non-US users(Crypto)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects crypto as payment method
    And user clicks on the links on Privacy Policy
    Then user should see privacy page in a new tab

    #    crypto error in portal
#  @tts
#  Scenario: Check invoice payment via BTC(Non-US)
#    Given a valid invoice url for Non-US users
#    When user expends payment method dropdown
#    And user selects crypto as payment method
#    And user selects BTC currency
#    And clicks on agreement checkbox and clicks on CONFIRM button
#    Then map3 should appear

  @inv_non_us @card_non_us
  Scenario: Check Terms and Conditions redirection links for Non-us users(Card)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects card as payment method
    And user clicks on the links on Terms and Conditions
    Then user should see terms page in a new tab

  @inv_non_us @card_non_us
  Scenario: Check Membership Agreement redirection links for Non-us users(Card)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects card as payment method
    And user clicks on the links on Membership Agreement
    Then user should see membership agreement page in a new tab

  @inv_non_us @card_non_us
  Scenario: Check invoice payment via Card(Non-us)
    Given a valid invoice url for Non-US users
    When user expends payment method dropdown
    And user selects card as payment method
    And clicks on agreement checkbox and clicks on CONFIRM button
    And enter card details in Stripe and clicks on pay
    Then a payment success message should appear

  @inv_non_us @card_non_us
  Scenario: Check a already paid invoice link(Non-us)
    Given user clicks on a Non-US invoice link that has already been paid
    Then user should see an already paid error message

  @inv_non_us
  Scenario: Check an expired invoice link(Non-us)
    Given a "BD" invoice link that has been expired
    When user clicks on the invoice url
    Then user should see an expired error message


  @inv_non_us @crypto_non_us
  Scenario: Check minimum amount for crypto currency(Non-us)
    Given a invoice url of amount "10" USD for "BD" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects crypto as payment method
    And user selects ETH crypto currency
    Then an error message with minimum crypto amount should appear

  @inv_non_us @crypto_non_us
  Scenario: Check maximum amount for ETH,LTC,USDT currency(Non-us)
    Given a invoice url of amount "110000" USD for "BD" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects crypto as payment method
    And user selects ETH,LTC,USDT crypto currency
    Then an error message with maximum crypto amount should appear

  @inv_non_us @crypto_non_us
  Scenario: Check maximum amount for BTC currency(Non-us)
    Given a invoice url of amount "200000" USD for "BD" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects crypto as payment method
    Then an error message for maximum BTC should appear

  @inv_non_us @card_non_us
  Scenario: Check invoice payment via invalid Card(Non-us)
    Given  a valid invoice url for "BD" users
    When user clicks on the invoice url
    And user expends payment method dropdown
    And user selects card as payment method
    And clicks on agreement checkbox and clicks on CONFIRM button
    And enter invalid card details in Stripe and clicks on pay
    Then a payment error message should appear