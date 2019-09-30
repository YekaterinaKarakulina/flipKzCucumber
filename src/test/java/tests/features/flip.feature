Feature: Flip site`s Author and Publication Year filters
  #Tests checks working of "Authors" filter in "flip.kz" website with one author and several authors
  #Tests checks working of "Publication year" filter in "flip.kz" website with from/to/from-to values

 Background:
    Given Website flip.kz is opened
   When User does login

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User selects random author
    And User moves to random result`s page, selects random book
    Then Random book`s author from result page is selected author

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User selects random authors
    And User moves to random result`s page, selects random book
    Then Random book`s author from result page is one of the selected authors

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User selects 2 authors
    And User moves to random result`s page, selects random book
    Then Random book`s author from result page is one of the selected authors

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User set publication year first value
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is greater or equal then user entered

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User set publication year last value
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is lower or equal then user entered

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User set publication year range
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is on range user entered

  Scenario:
    Given User is authorized
    When User navigates to imaginative literature section
    And User selects random author
    And User set publication year range
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is on range user entered

  Scenario Outline: outline
    Given User is authorized
    When User navigates to imaginative literature section
    And User set <from> and <to> range values
    And User moves to random result`s page, selects random book
    Then Random book`s publication year from result page is on range user entered

    Examples:
      | from | to   |
      | 2002 | 2010 |
      | 2005 | 2015 |
      | 2013 | 2018 |
