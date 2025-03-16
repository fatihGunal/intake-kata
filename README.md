### WineShop Requirements
Hi there. We are running a small wine shop in Ghent. The default price setting we are using is quite basic. 

We are quickly expanding, and we want to be able to easily change existing price rules and add new categories.

To summarize the existing current application:

- The current code is working as expected;
- All wines have a `expiresInYears` value which shows us how many years we have left before the wine expires;
- All wines have a `price`
- At the end of the year, we lower the `expiresInYears` and `price` values but
    - We don't like throwing away wine, so even when it expires we want to sell it. If the wine is expired, the `price` degrades twice as fast.
    - The price can off course never go negative, we are not going to pay to get rid of our expired wine!
    - We have some cellar or aging wines, so the price increases instead of decreases.
        - When it expires, it becomes even more valuable and increases its value twice as fast!
    - We are decent vendors, so the price can never go above 100 euro due to aging of our wine. 
    - We have a special wine in our inventory `Wine brewed by Alexander the Great`. It is not a wine meant for consumption but a collectors item, and we don't want to change its price due to age.
        - It never expires
        - So price never changes due to aging
    - We have another special one which is `Event Wine`. It is a Wine we introduced specifically for special events (like the first trip to Mars).
        - It, like cellar or aging wine increases in value over time,
        - When there are only 7 years (or less) before expiration, the price increases with 2,
        - When there are only 2 years (or less) before expiration, the price increases with 4 however,
        - But when the event has passed (see `expiresInYears`), it drops to zero to indicate we have to remove it from our inventory.

We have recently discovered a new market segment which like 'eco-brewed' wines. Sadly enough they degrade really fast hence the price degrades twice as fast as a regular wine. Can you introduce this new type?

A few constraints we have to consider:

* You **cannot** change the Wine class
* The WineShop constructor argument should not be changed.
* Do small commits / changes so we can follow your 'way of thinking'

We have multiple inventory printouts<br/> 
**3 years ago :** 
```
wine, price, expiresInYears
Wine(name=Standard Wine, price=20, expiresInYears=10)
Wine(name=Bourdeaux Conservato, price=0, expiresInYears=2)
Wine(name=Another Standard Wine, price=7, expiresInYears=5)
Wine(name=Wine brewed by Alexander the Great, price=150, expiresInYears=0)
Wine(name=Wine brewed by Alexander the Great, price=80, expiresInYears=10)
Wine(name=Event Wine, price=20, expiresInYears=15)
Wine(name=Event Wine, price=49, expiresInYears=10)
Wine(name=Event Wine, price=49, expiresInYears=5)
Wine(name=Eco Brilliant Wine, price=6, expiresInYears=3)
```

**today:**
```
wine, price, expiresInYears
Wine(name=Standard Wine, price=17, expiresInYears=7)
Wine(name=Bourdeaux Conservato, price=4, expiresInYears=-1)
Wine(name=Another Standard Wine, price=4, expiresInYears=2)
Wine(name=Wine brewed by Alexander the Great, price=150, expiresInYears=0)
Wine(name=Wine brewed by Alexander the Great, price=80, expiresInYears=10)
Wine(name=Event Wine, price=23, expiresInYears=12)
Wine(name=Event Wine, price=52, expiresInYears=7)
Wine(name=Event Wine, price=55, expiresInYears=2)
Wine(name=Eco Brilliant Wine, price=3, expiresInYears=0)
```

to give you an idea how values change over time...
As you can see the Eco Brilliant Wine is still handled as a regular wine :(

### Exercise comments
This is a refactoring kata. The business case is fiction so we take no responsibility regarding our specific pricing rules for selling Wine.

### Conclusion
Have a good glass of wine :
```
               __
              )==(
              )==(
              |H |
              |H |
              |H |
             /====\
            / Dr S \
           /========\
          :HHHHHHHH H:
          |HHHHHHHH H|
          |HHHHHHHH H|
          |HHHHHHHH H|
   \______|=/========\________/
    \     :/oO/      |\      /
     \    / oOOOThis | \    /
      \__/| Is=GIldEd|  \__/
       )( |00Rose====|   )(
       )( |°0=Kata==|   )(
       )( |==========|   )(
       )( |HHHHHHHH H|   )(
      .)(.|HHHHHHHH H|  .)(.
     ~~~~~~~~~~~~~~~~  ~~~~~~
```