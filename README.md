Roman Numeral Calculator
==============================

_Programming interviews suck. We know. You know. We know that you know._

_We don’t care if you’ve read “Cracking The Coding Interview” or got onto The Dean’s Honors List this quarter. We’re just looking for good programmers._

_Yeah, we could post to Indeed or Monster and then put you through an awkward phone screen with a recruiter who can’t code, or…you could take our sweet coding challenge and maybe win a Raspberry Pi while you’re at it.
Sound good?_

---------------------
Submit a pull request to this project by Sunday, April 2nd @ 1am. Winner will be announced/contacted on Sunday April 2nd by 10am.
The person with the most tests passing from our test suite gets to take home a Raspberry Pi 3, complete with 802.11n Wireless LAN, Bluetooth 4.1, 1GB of RAM, and HDMI & Ethernet ports.
Any ties will be broken by code review, based on readability and correctness.

Be sure to include your contact information in _ContactInfo.txt_

Here’s your challenge:

This github repo contains a set of partially implemented functions constituting a Roman numeral calculator. These include addition, subtraction, multiplication, and division.

Using the test suite as your guide, fully implement each function.

Each function’s input will be an array of strings representing Roman numeral values.

Each function’s output will be a string representing Roman numeral values.
Note that 0 in Roman numerals is represented by the string “nulla”.

`3 + 2  = 5 in Roman numerals is:
III + II = V`

`3 + -3 = 0 in Roman numerals is:
III + -III = nulla`

Because order matters for subtraction and division, “subtractRomanNumerals” and “divideRomanNumerals” will take in an initial value, followed by the rest of the numbers to be operated on.

Pseudocode:

`subtractRomanNumerals(5, [4, 1]) => 5 - 4 - 1`

`divideRomanNumerals(21, [3, 3]) => 21 / 3 / 3`

You must also support negative numbers and fractional values. Admittedly, the concept of non-positive integers and fractions don’t exist in traditional Roman numerals, so we’re going to represent them as lowercase numerals (see below for examples). Fractions should be simplified and rounded to the hundredths place.

`2.543 => 2 54/100 => 2 27/50 => II xxvii/l`

Pro tips:
- Don’t try to write the perfect solution right away. Build your program piece-by-piece and focus on getting one test to pass at a time. At CJ, we value Test Driven Development (TDD) because it helps us break down complex problems into smaller, solvable chunks.

- This project is meant to be challenging and we don’t expect you to get all of the tests passing. Just try to get as many passing as you can. Remember — the winner is the person with the _MOST_ of our 20 tests passing.

- If you have any questions, please stop by the CJ Engineering booth to speak with a CJ engineer.

- These sites may be helpful:
    - http://www.onlineconversion.com/roman_numerals_advanced.htm
    - http://www.factmonster.com/ipka/A0769547.html
