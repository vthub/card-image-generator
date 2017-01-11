#Credit Card Image Generator

This service generates credit card image for a given credit card number, expiry date and card holder name.

Generated image can be used as an image in documentation, for OCR recognition test purposes or for any other kind of use cases where it can be of help.

## Getting started

To start the service you can use gradle:

```
./gradlew run
```

By default service will start on port **5050**.

To generate a card with a random card number simply go to: <http://localhost:5050/card>

## Customizing the card details

You can customize values displayed on the card:

### Card number

To generate card with specific card number append the card number after the full url. For example: <http://localhost:5050/card/1234567890123456>
Card number can have any length between 1 and 19 digits.

### Expiry date

To use specific expiry date use query parameter `expiry`.
For example, to have expiry date `11/21` printed on a card, use the following path: `/card/?expiry=11/21`.

### Card Holder name

You can also specify card holder first and last name. To do this use `name` and `lastname` query parameters respectively. For example `card/?name=John&lastname=Smith`. 

## Customizing the card output

### Size

You can specify the width of the final output image using `width` query parameter.

For example, to have a card of size `500px` use the following uri: `/card/?width=500`.

Maximum allowed size `1000px`, the smallest card you can generate is `200px`.

# Example

Let's say you want to generate a card that has the following data printed on it:

* card number `1234 5678 9012 3456`
* expiry date `11/21`
* card holder name `John Smith`

The url that will allow you to generate an image with specified data is:

<http://localhost:5050/card/1234567890123456?expiry=11/21&name=John&lastname=Smith>

The result will be as follows:

![Example card](https://github.com/vthub/card-image-generator/raw/master/src/main/resources/example-card.png "Example card")
