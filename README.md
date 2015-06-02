# multiprime

Solution to coding challenge posed by
[name omitted in case they don't want solutions easily found via google].

The program prints a multiplication table of the first 10 primes.

It prints an X at the beginning of the first row.
That behavior was unspecified, but something (or nothing) had to go there,
and "X" seemed better than "1" (and is fairly standard in a times table).
Had this been a real-world program with a real customer/user,
I'd have asked for clarification.
(As is, if it's a problem, let me know!  I can fix it.)

## Installation

Clone this repo!
(This instruction assumes you already have leiningen.  And git.)

## Usage

Use leiningen to run it.  Perhaps overkill, but quite handy.

    $ lein run [n]

You can specify a number of primes on the command line.
If you don't, it defaults to 10.

You may wish to run the tests.

    $ lein test

### Bugs

If you give a command-line arg that is something other than an integer,
you get an ugly error.  The problem statement didn't mention command-line
args at all, so I didn't worry about making that part robust.

The output is tab-separated, so if you have results with more than 7 digits,
it won't line up well.  But your terminal is probably not that big.
Also, it means the numbers are left-justified.

## License

Copyright © 2015

