UTEID: tz2853; tel455;
FIRSTNAME: Tianhang; Thi Edison;
LASTNAME: Zhu; Le;
CSACCOUNT: zthfmh; thi;
EMAIL: zthfmh@gmail.com; thi_le@utexas.edu;

[Program 4]
[Description]
There are 4 java files: AES.java, InverseTable.java, Table.java and Rcon.java. The main function sits in AES.java which also contains most of other key methods. The main method takes in input files and then generate the expanded key by calling keyExpansion() for both encoding and decoding process. The two most important methods, encryption() and decryption() then does each job by executing the corresponding 4 helper methods: addRoundKey(), mixColumn/mixColumn_inverse(), shiftRows/ShiftRows_inverse() amd subBytes/subBytes_inverse() in different orders. We used the provided mixColumn methods and modified to accomodate our implementation, as well as incorporating the key expansion algorithm provided on the documentation. In addition to AES.java, InverseTable.java, Table.java, and Rcon.java are used as containers to store the inversed s-box, s-box and rcon table, respectively. To compile our program, you need to use "javac *.java". To run our program, you need to use "java AES e keyfile testfile" for encrypting and "java AES d keyfile testfile.enc" for decrypting.


[Finish]
We finished the entire assignment.

[Test Case 1]

[Command line]
java AES e key test1
java AES d key test1.enc

diff test1 test1.enc.dec

[Timing Information]
Encoding time was 0.123 seconds and file size was 1.65kb so throughput is 13.415 kb/s.
Decoding time was 0.123 seconds and file size was 1.65kb so throughput is 13.415 kb/s.

[Input Filenames]
test1
key

[Output Filenames]
test1.enc
test1.enc.dec



[Test Case 2]

[Command line]
java AES e key test2
java AES d key test2.enc

diff test2 test2.enc.dec

[Timing Information]
Encoding time was 0.198 seconds and file size was 3.30kb so throughput is 16.667 kb/s.
Decoding time was 0.200 seconds and file size was 3.30kb so throughput is 16.500 kb/s.

[Input Filenames]
test2
key

[Output Filenames]
test2.enc
test2.enc.dec



[Test Case 3]

[Command line]
java AES e key test3
java AES d key test3.enc

diff test3 test3.enc.dec

[Timing Information]
Encoding time was 0.270 seconds and file size was 4.95kb so throughput is 18.333 kb/s.
Decoding time was 0.278 seconds and file size was 4.95kb so throughput is 17.806 kb/s.

[Input Filenames]
test3
key

[Output Filenames]
test3.enc
test3.enc.dec


[Test Case 4]

[Command line]
java AES e key test4
java AES d key test4.enc

diff test4 test4.enc.dec


[Timing Information]
Encoding time was 0.686 seconds and file size was 16.5kb so throughput is 24.052 kb/s.
Decoding time was 0.688 seconds and file size was 16.5kb so throughput is 23.983 kb/s.

[Input Filenames]
test4
key

[Output Filenames]
test4.enc
test4.enc.dec


