UTEID: tz2853; tel455;
FIRSTNAME: Tianhang; Thi Edison;
LASTNAME: Zhu; Le;
CSACCOUNT: zthfmh; thi;
EMAIL: zthfmh@gmail.com; thi_le@utexas.edu;

[Program 1]
[Description]
There is only 1 java file called Passwords.java. In the main method it takes in all three parameters from the console and firstly convert the reference text to all lower case line by line, the 2d followerTable is then generated following the instruction; We also make the starters table during this process. After that, we then generate the counts array by adding all values in the same row for each letter. The next step is to generate another table for the initial letters based on their frequency of occurences or the frequency table of the previous letter for any middle letter, by concatinating those letters we get the final password for each round. There are three other helper methods: printArray() which prints the followerTable, totalValue_1() that adds up all integer values in an array, and getPool() which helps to convert the counting table to the frequency table mentioned above. To compile our program, you need to use "javac *.java". To run our program, you need to use "java Passwords reference-file N k" where N is the number of passwords to generate and k is the number of characters in a password.

Note1: If a seed is needed for random number generation, it can be inserted on the line 74.
Note2: In the case where the first character of a word is a non-English character, we assumed that for instance, "@My" and "My" are two different words because the starting characters are different (one is a "@" and the other is a "M"). Thus we're assuming that for the word "@My" the first character is "@" instead of "M". This affects the frequency of the characters in the starters table.
Note3: We have included the followers table in the output because it's mentioned by the documentation. If the output looks misaligned, please maximize the window to view the correct tabular representation of the output.


[Finish]
We finished the entire assignment.

[Source of Reference File]
The size of the file is 56.9kB called Pride500.txt, we found it on Gutenberg and truncated it to make it shorter; the link is https://www.gutenberg.org/ebooks/1342
We truncated the file to 500 lines and there are totally 10085 words. Another reference file is the original Pride and Prejudice text before truncation which is called Pride.txt.


[Test Cases]
[Input of test 1]
java Passwords Pride500.txt 15 9

[Output of test 1]
      a     b     c     d     e     f     g     h     i     j     k     l     m     n     o     p     q     r     s     t     u     v     w     x     y     z 
a     0   108    85   199     1    27    55     1   131     0    49   216    81   724     2    41     0   339   402   392    45    79    16     0    94     0 
b    31     0     0     0   335     0     0     0    70    12     0    78     0     0    58     0     0    25    10     5    87     0     0     0    61     0 
c   124     0    19     0   202     0     0   175    47     0    32    21     0     0   204     0    12    30     1    78    18     0     0     0    41     0 
d   114     0     0    10   211     0    13     0   150     1     0     9    15     5    80     0     1    19    36     0    16     9     1     0    32     0 
e   249     4    91   420   154    44    27     6    88     0     4   257   112   510     9    43    13   918   285   234     0   113    28    52   126     0 
f    61     0     0     0    84    43     0     0    87     0     0    10     0     1   177     0     0    56     0    27    20     0     0     0     2     0 
g    31     6     0     1   109     0     1   125    47     0     0    76     1    10    51     0     0    54    16     4    15     0     0     0     1     0 
h   462     9     0     1  1175     0     0     1   339     0     0     4     6     0   229     0     0    11     1    67    29     0     0     0     6     0 
i    43    15   103   129   146    81    70     0     0     0    34   128   132   811   135    12     1   133   451   397     1    65     0    12     0    59 
j    37     0     0     0    14     0     0     0     0     0     0     0     0     0    11     0     0     0     0     0    16     0     0     0     0     0 
k     1     0     0     0   104     5     0     0    42     0     0     4     0    44     0     0     0     0     3     0     0     0     0     0     3     0 
l   107     0     1   137   281    47     0     0   260     0    19   239     5     3   104     4     0     1    24    14    37    10    15     0   189     0 
m   165    16     0     0   234     2     0     0   125     0     0     0    27     0   112    62     0   125    21     1    72     0     0     0    68     0 
n    35     0   138   475   366     4   426     2    75     6    33    51     0    86   302     0     3     1    91   258    12    24     5     3    40     0 
o     7    26    17    61    15   308     9    11    19     0    21    61   180   443   103    43     0   290    74   233   538    37   153     1     9     0 
p    68     0     0     0   112     0     0     4    29     0     0    84     0     0    67    30     0    86    12    33    15     0     0     0     2     0 
q     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    59     0     0     0     0     0 
r    96     2    53    44   574    24    14    14   144     0     9    44    31    39   117     8     0    28   183   121    13    18     3     0   124     0 
s   126     6    27     0   295     4     2   200   187     0    13    10     7     1   146    47     0     0   148   281   108     0    15     0     8     0 
t   116     0     6     0   341     8     0  1107   228     0     0    48     4    12   413     0     0    54    23    91    61     0    40     0    71     0 
u    33    12   110    23    29     5    78     0    34     0     0   120    18   109     0    34     0   208   123   143     0     0     0     0     0     0 
v    28     0     0     0   343     0     0     0    66     0     0     0     0     0    13     0     0     0     0     0     0     0     0     0     0     0 
w   219     0     1     0   145     0     0   170   214     0     0     7     0    40   100     0     0     1     7     0     0     0     0     0     0     0 
x     4     0    12     0     8     0     0     1     7     0     0     0     0     0     0    14     0     0     0    18     0     0     0     0     0     0 
y     0    12     0    10    33     2     0     0    12     0     0     1     4     1   213     1     0     0    31    19     0     0     1     0     0     0 
z    46     0     0     0     1     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    12    12 
Passwords are: 
 ondsaroll
 asiesiner
 towedisti
 thanxchas
 uiabucust
 murgeavom
 tcoreanou
 plenckndg
 outhtyooh
 ouneappot
 fosuantth
 hitepppas
 wishtorst
 hengimobi
 airgabeel

   
[Input of test 2]
java Passwords Pride500.txt 25 20

[Output of test 2]
      a     b     c     d     e     f     g     h     i     j     k     l     m     n     o     p     q     r     s     t     u     v     w     x     y     z 
a     0   108    85   199     1    27    55     1   131     0    49   216    81   724     2    41     0   339   402   392    45    79    16     0    94     0 
b    31     0     0     0   335     0     0     0    70    12     0    78     0     0    58     0     0    25    10     5    87     0     0     0    61     0 
c   124     0    19     0   202     0     0   175    47     0    32    21     0     0   204     0    12    30     1    78    18     0     0     0    41     0 
d   114     0     0    10   211     0    13     0   150     1     0     9    15     5    80     0     1    19    36     0    16     9     1     0    32     0 
e   249     4    91   420   154    44    27     6    88     0     4   257   112   510     9    43    13   918   285   234     0   113    28    52   126     0 
f    61     0     0     0    84    43     0     0    87     0     0    10     0     1   177     0     0    56     0    27    20     0     0     0     2     0 
g    31     6     0     1   109     0     1   125    47     0     0    76     1    10    51     0     0    54    16     4    15     0     0     0     1     0 
h   462     9     0     1  1175     0     0     1   339     0     0     4     6     0   229     0     0    11     1    67    29     0     0     0     6     0 
i    43    15   103   129   146    81    70     0     0     0    34   128   132   811   135    12     1   133   451   397     1    65     0    12     0    59 
j    37     0     0     0    14     0     0     0     0     0     0     0     0     0    11     0     0     0     0     0    16     0     0     0     0     0 
k     1     0     0     0   104     5     0     0    42     0     0     4     0    44     0     0     0     0     3     0     0     0     0     0     3     0 
l   107     0     1   137   281    47     0     0   260     0    19   239     5     3   104     4     0     1    24    14    37    10    15     0   189     0 
m   165    16     0     0   234     2     0     0   125     0     0     0    27     0   112    62     0   125    21     1    72     0     0     0    68     0 
n    35     0   138   475   366     4   426     2    75     6    33    51     0    86   302     0     3     1    91   258    12    24     5     3    40     0 
o     7    26    17    61    15   308     9    11    19     0    21    61   180   443   103    43     0   290    74   233   538    37   153     1     9     0 
p    68     0     0     0   112     0     0     4    29     0     0    84     0     0    67    30     0    86    12    33    15     0     0     0     2     0 
q     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    59     0     0     0     0     0 
r    96     2    53    44   574    24    14    14   144     0     9    44    31    39   117     8     0    28   183   121    13    18     3     0   124     0 
s   126     6    27     0   295     4     2   200   187     0    13    10     7     1   146    47     0     0   148   281   108     0    15     0     8     0 
t   116     0     6     0   341     8     0  1107   228     0     0    48     4    12   413     0     0    54    23    91    61     0    40     0    71     0 
u    33    12   110    23    29     5    78     0    34     0     0   120    18   109     0    34     0   208   123   143     0     0     0     0     0     0 
v    28     0     0     0   343     0     0     0    66     0     0     0     0     0    13     0     0     0     0     0     0     0     0     0     0     0 
w   219     0     1     0   145     0     0   170   214     0     0     7     0    40   100     0     0     1     7     0     0     0     0     0     0     0 
x     4     0    12     0     8     0     0     1     7     0     0     0     0     0     0    14     0     0     0    18     0     0     0     0     0     0 
y     0    12     0    10    33     2     0     0    12     0     0     1     4     1   213     1     0     0    31    19     0     0     1     0     0     0 
z    46     0     0     0     1     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    12    12 
Passwords are: 
 afontthaberiserenind
 sorserosondudewideth
 heseandinghethedatrl
 raddrerlaremunikndof
 weayeremumrnedechenn
 imittadinereanotenen
 esexppandurmaresther
 athellienkedellencin
 sinderramintherythoo
 ouisourereaisustluma
 rasothelyouceseenthe
 mrschelyowandgheluth
 kncotimatorgherverli
 aleryemolarceryshith
 nglyogisonevetongred
 woursyofighthodemric
 thicyowhondeeturvind
 mucyonsanengisheacan
 warsscusoursandented
 ghagisenonintherersu
 morivemyenencineanje
 thaisterferaineainel
 tofontathesenshentit
 tendvetungaleerermis
 sfotitlinowadeveeyon


[Input of test 3]
java Passwords Pride500.txt 50 12

[Output of test 3]
      a     b     c     d     e     f     g     h     i     j     k     l     m     n     o     p     q     r     s     t     u     v     w     x     y     z 
a     0   108    85   199     1    27    55     1   131     0    49   216    81   724     2    41     0   339   402   392    45    79    16     0    94     0 
b    31     0     0     0   335     0     0     0    70    12     0    78     0     0    58     0     0    25    10     5    87     0     0     0    61     0 
c   124     0    19     0   202     0     0   175    47     0    32    21     0     0   204     0    12    30     1    78    18     0     0     0    41     0 
d   114     0     0    10   211     0    13     0   150     1     0     9    15     5    80     0     1    19    36     0    16     9     1     0    32     0 
e   249     4    91   420   154    44    27     6    88     0     4   257   112   510     9    43    13   918   285   234     0   113    28    52   126     0 
f    61     0     0     0    84    43     0     0    87     0     0    10     0     1   177     0     0    56     0    27    20     0     0     0     2     0 
g    31     6     0     1   109     0     1   125    47     0     0    76     1    10    51     0     0    54    16     4    15     0     0     0     1     0 
h   462     9     0     1  1175     0     0     1   339     0     0     4     6     0   229     0     0    11     1    67    29     0     0     0     6     0 
i    43    15   103   129   146    81    70     0     0     0    34   128   132   811   135    12     1   133   451   397     1    65     0    12     0    59 
j    37     0     0     0    14     0     0     0     0     0     0     0     0     0    11     0     0     0     0     0    16     0     0     0     0     0 
k     1     0     0     0   104     5     0     0    42     0     0     4     0    44     0     0     0     0     3     0     0     0     0     0     3     0 
l   107     0     1   137   281    47     0     0   260     0    19   239     5     3   104     4     0     1    24    14    37    10    15     0   189     0 
m   165    16     0     0   234     2     0     0   125     0     0     0    27     0   112    62     0   125    21     1    72     0     0     0    68     0 
n    35     0   138   475   366     4   426     2    75     6    33    51     0    86   302     0     3     1    91   258    12    24     5     3    40     0 
o     7    26    17    61    15   308     9    11    19     0    21    61   180   443   103    43     0   290    74   233   538    37   153     1     9     0 
p    68     0     0     0   112     0     0     4    29     0     0    84     0     0    67    30     0    86    12    33    15     0     0     0     2     0 
q     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    59     0     0     0     0     0 
r    96     2    53    44   574    24    14    14   144     0     9    44    31    39   117     8     0    28   183   121    13    18     3     0   124     0 
s   126     6    27     0   295     4     2   200   187     0    13    10     7     1   146    47     0     0   148   281   108     0    15     0     8     0 
t   116     0     6     0   341     8     0  1107   228     0     0    48     4    12   413     0     0    54    23    91    61     0    40     0    71     0 
u    33    12   110    23    29     5    78     0    34     0     0   120    18   109     0    34     0   208   123   143     0     0     0     0     0     0 
v    28     0     0     0   343     0     0     0    66     0     0     0     0     0    13     0     0     0     0     0     0     0     0     0     0     0 
w   219     0     1     0   145     0     0   170   214     0     0     7     0    40   100     0     0     1     7     0     0     0     0     0     0     0 
x     4     0    12     0     8     0     0     1     7     0     0     0     0     0     0    14     0     0     0    18     0     0     0     0     0     0 
y     0    12     0    10    33     2     0     0    12     0     0     1     4     1   213     1     0     0    31    19     0     0     1     0     0     0 
z    46     0     0     0     1     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    12    12 
Passwords are: 
 henelasppend
 himrshedithi
 frorederenyo
 materyomison
 vicideapondr
 thasobedehin
 hlllesevioth
 thanesthofad
 fouttorcuger
 tideleleryof
 ndonlysetyon
 giritherowar
 feldirthecer
 thannoshable
 ithiaysemasa
 tusacarsewif
 heactenarser
 weanomaronof
 anenkednnero
 hetererthere
 thaknglfuain
 orpodersisio
 hudigeyomert
 ndisckithers
 doutinderoou
 wlaresefenou
 blyonglenoth
 ponlioulyong
 myorexerillv
 icoreyithare
 upofisonooti
 tereyersopem
 icrethenthau
 berrndontoua
 stharvilplis
 eyouternougl
 lelixcqugely
 attheandulea
 withamepleri
 stnnoffavenc
 itemoftlllly
 thentesthins
 ingistofinor
 ketuthinderd
 thouareshest
 ksthisealeng
 hondendetorl
 coouasulirer
 thimyshasshi
 wioussthemre


[Input of test 4]
java Passwords Pride500.txt 100 2

[Output of test 4]
      a     b     c     d     e     f     g     h     i     j     k     l     m     n     o     p     q     r     s     t     u     v     w     x     y     z 
a     0   108    85   199     1    27    55     1   131     0    49   216    81   724     2    41     0   339   402   392    45    79    16     0    94     0 
b    31     0     0     0   335     0     0     0    70    12     0    78     0     0    58     0     0    25    10     5    87     0     0     0    61     0 
c   124     0    19     0   202     0     0   175    47     0    32    21     0     0   204     0    12    30     1    78    18     0     0     0    41     0 
d   114     0     0    10   211     0    13     0   150     1     0     9    15     5    80     0     1    19    36     0    16     9     1     0    32     0 
e   249     4    91   420   154    44    27     6    88     0     4   257   112   510     9    43    13   918   285   234     0   113    28    52   126     0 
f    61     0     0     0    84    43     0     0    87     0     0    10     0     1   177     0     0    56     0    27    20     0     0     0     2     0 
g    31     6     0     1   109     0     1   125    47     0     0    76     1    10    51     0     0    54    16     4    15     0     0     0     1     0 
h   462     9     0     1  1175     0     0     1   339     0     0     4     6     0   229     0     0    11     1    67    29     0     0     0     6     0 
i    43    15   103   129   146    81    70     0     0     0    34   128   132   811   135    12     1   133   451   397     1    65     0    12     0    59 
j    37     0     0     0    14     0     0     0     0     0     0     0     0     0    11     0     0     0     0     0    16     0     0     0     0     0 
k     1     0     0     0   104     5     0     0    42     0     0     4     0    44     0     0     0     0     3     0     0     0     0     0     3     0 
l   107     0     1   137   281    47     0     0   260     0    19   239     5     3   104     4     0     1    24    14    37    10    15     0   189     0 
m   165    16     0     0   234     2     0     0   125     0     0     0    27     0   112    62     0   125    21     1    72     0     0     0    68     0 
n    35     0   138   475   366     4   426     2    75     6    33    51     0    86   302     0     3     1    91   258    12    24     5     3    40     0 
o     7    26    17    61    15   308     9    11    19     0    21    61   180   443   103    43     0   290    74   233   538    37   153     1     9     0 
p    68     0     0     0   112     0     0     4    29     0     0    84     0     0    67    30     0    86    12    33    15     0     0     0     2     0 
q     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    59     0     0     0     0     0 
r    96     2    53    44   574    24    14    14   144     0     9    44    31    39   117     8     0    28   183   121    13    18     3     0   124     0 
s   126     6    27     0   295     4     2   200   187     0    13    10     7     1   146    47     0     0   148   281   108     0    15     0     8     0 
t   116     0     6     0   341     8     0  1107   228     0     0    48     4    12   413     0     0    54    23    91    61     0    40     0    71     0 
u    33    12   110    23    29     5    78     0    34     0     0   120    18   109     0    34     0   208   123   143     0     0     0     0     0     0 
v    28     0     0     0   343     0     0     0    66     0     0     0     0     0    13     0     0     0     0     0     0     0     0     0     0     0 
w   219     0     1     0   145     0     0   170   214     0     0     7     0    40   100     0     0     1     7     0     0     0     0     0     0     0 
x     4     0    12     0     8     0     0     1     7     0     0     0     0     0     0    14     0     0     0    18     0     0     0     0     0     0 
y     0    12     0    10    33     2     0     0    12     0     0     1     4     1   213     1     0     0    31    19     0     0     1     0     0     0 
z    46     0     0     0     1     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0     0    12    12 
Passwords are: 
 ha
 to
 th
 sp
 sp
 bu
 de
 me
 on
 th
 ha
 as
 ss
 is
 co
 th
 th
 wi
 de
 ve
 st
 de
 ne
 te
 ar
 ge
 he
 ff
 ie
 cc
 si
 mr
 ot
 ed
 th
 ge
 hi
 al
 fe
 om
 bi
 yo
 wi
 he
 we
 om
 th
 in
 wa
 ed
 mp
 in
 lo
 th
 wn
 tt
 ve
 hi
 ex
 re
 au
 lt
 ti
 th
 ag
 su
 da
 be
 te
 ca
 ei
 si
 sa
 le
 th
 go
 ci
 on
 he
 st
 th
 pe
 ba
 he
 op
 ut
 tt
 an
 ot
 bu
 ho
 tl
 so
 as
 ec
 ar
 te
 th
 le
 mr
