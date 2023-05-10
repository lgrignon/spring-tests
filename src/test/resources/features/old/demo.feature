Feature: Demo feature
    Scenario: This is a scenario to test demo feature
        Given the following products:
            | lamp   | 20 | 
            | milk | 5 | 
            | wheelchair | 230 |  
            And the following catalog:
                | 1 | 2 |
        When I select liquid products
        Then 1 product selected and name is "milk"
    
    Scenario: This is another scenario to test demo feature
        Given the following products:
            | water   |
            | milk |
            | coca |
            | truck | 
            | house | 
        When I select first product which is not liquid
        Then 1 products selected and name is "truck"