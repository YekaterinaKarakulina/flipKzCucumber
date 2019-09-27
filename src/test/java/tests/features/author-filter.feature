Feature: Author filter
  #Tests checks working of "Authors" filter in "flip.kz" website with one author and several authors

  Background:
    Given Website flip.kz is opened
    When User does login

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User selects random book author
    And User moves to random result`s page, selects random book
    Then Random book`s author from result page is selected author

  Scenario:
    Given User is authorized
    Then User navigates to imaginative literature section
    And User selects random book authors
    And User moves to random result`s page, selects random book
    Then Random book`s author from result page is one of the selected authors

  Scenario:
    Given User is authorized
    Then User navigates to imaginative literature section
    And User set publication year first value
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is greater or equal then user entered

  Scenario:
    Given User is authorized
    Then User navigates to imaginative literature section
    And User set publication year last value
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is lower or equal then user entered

  Scenario:
    Given User is authorized
    Then User navigates to imaginative literature section
    And User set publication year range
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is on range user entered

  Scenario:
    Given User is authorized
    Then User navigates to imaginative literature section
    And User selects random book author
    And User set publication year range
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is on range user entered


