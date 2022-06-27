# Blacklist Name Matching
Program to find the best match in a given blacklist.

## General info
This program has three input parameters: 
* **input** - The name to validate against blacklist.
* **blacklist** - File that contains one name per line. Blacklist names are stored in `src/main/resources/names.txt`.
* **noise words** - File that contains one noise word per line. Noise words are stored in `src/main/resources/noise_words.txt`.

Firstly, program removes noise words from input.
Then it searches if any word from input matches with a name in the blacklist.
If program finds a match, it calculates score based on similarities and differences using Levenshtein distance. 
When scores are found, program returns the best possible match by highest score (the best score can be 1.0).

#### Example:
input:`to the osama laden`

blacklist:
```
osama bin laden
...
```
noise words:
```
to
the
...
```

* Remove noise word from input: `osama laden`
* Loop blacklist and find match a match: `osama bin laden`
* Calculate score: `2/3 + 1/(3+3) = ~0.833` where
    * `2/3` - similar words/total amount of blacklist words
    * `1/(3+3)` - different words/(total amount of blacklist words + Levenshtein distance)

## Technologies
Project is created with:
* Java 17
* Maven

## Setup
To run this project, install it locally and run via IDE.
