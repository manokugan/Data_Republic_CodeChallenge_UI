Feature: Search Products

Background: 
Given Ebay Page is Loaded

Scenario: Search Product1 in Ebay
When Search Product "Plain White Coffee Mug"
Then add the option to cart
When Search Product "Black Sunglass unisex" 
Then add the option to cart
When click viewCart icon
Then The items "Plain White Coffee Mug" and "Black Sunglass unisex" are available