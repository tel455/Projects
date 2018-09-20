UTEID: tz2853; tel455;
FIRSTNAME: Tianhang; Thi Edison;
LASTNAME: Zhu; Le;
CSACCOUNT: zthfmh; thi;
EMAIL: zthfmh@gmail.com; thi_le@utexas.edu;

[Program 1]
[Description]
[Part 1]
There are 6 java files: The main method is in SecureSystem.java and it serves as an entry point, which reads from a file on command line and parse the text line by line. For each line it sends the instruction to the reference monitor class, which recognizes each requested instruction and performs the corresponding security checks on the current objects and subjects by calling the checkSecurity() function (this function is also the core of our BLP implementation). If the action is allowed/legal, the reference monitor object then sends signal to the subject/object for action completion. The key idea of ensuring the system security as well as reserving the abstraction is to keep ObjectManager as a local class of the reference monnitor class, since that way no object can be acessed without going through the reference monitor. Besides the SecureSystem and ReferenceMonitor class, which are the two most important files to achieve the BLP features in part 1, there are also 4 other classes served as objected oriented use: Subjects and Objects classes for creating each individual subject and object; InstructionObject is a container class that stores each instruction, as well as checking the correctness of each instruction(done in the constructor); SecurityLevel class is as well a container class that hides subject/object's security level from themselves. To compile our program, you need to use "javac *.java". To run the program, you would need to use "java SecureSystem instructionList".


[Part 2]
There are 7 java files for part 2 (technically only 6, since we don't need the SecureSystem.java in this part). The main method is in CovertChannel.java and it serves as the entry point, which also reads the arguments from the command line. If the given file name is found under the directory, it read the file character by character and decompose each character into 8 bits. Depending on the value of each bit, the instruction performed by the higher level subject (hal) varies, then we perform the rest of 5 instructions for lyle since they are fixed all the time. All the instructions are then sent to the referenceMonitor for security check as mentioned in the first part. Since RUN is the last instruction, the run() method inside Subjects class will be invoked for each bit and it will recompose all bits until the number of total bits reaches 8, meaning it's the time to write the entire byte (a complete char) to the file. We realized that this might be the main reason that restricts our bandwidth and slowed the process down, since our implemetation requires an I/O access everytime a char is appeared, which is a huge limitation in the performance of our program. However, we think that this can be optimized by temporarily buffering a certain amount chars (e.g. 1kb) in the memory, then write the whole chunk to the disk to reduce the I/O accessing time. To display the duration time and the bandwidth, we detect if there's a verbose flag in the command argument. If the verbose is appeared, we also write each instruction to the log file using the printLog() method. In the case of part 2, the main method and the run() in Subjects class are the key methods that serve as decomposing byte into bits and vice versa. All other classes are used the same way described in part 1. To compile our program, you need to use "javac *.java". To run the program, you can either use "java CovertChannel v inputfilename" or "java CovertChannel inputfilename".

[Finish]
[Part 1]
We finished the entire part 1 of this assignment. 
Note1: Our output is under the assumption that there is an empty line in the end, like the sample output given on the documentation page.
Note2: In the original README file, under the "Output of test 1" section there is originally a line on the top of sample output that says "Reading from file: instructionList" which our program doesn't generate since on the documentation the sample output does not include this line. 

[Part 2]
We finished the entire part 2 of this assignment. Note: Since the documentation states that the program should "compute and write to standard output the timing and bandwidth for a given run", we assume that the "standard out" means displaying those values in the console using System.out.println().  

[Part 2, Machine Information]
We always worked on the cs lab machine in the basement. 
The machine type is:
Linux firefly 4.4.0-79-generic #100-Ubuntu SMP Wed May 17 19:58:14 UTC 2017 x86_64 x86_64 x86_64 GNU/Linux
The OS is: Ubuntu 16.04.2 LTS 64-bit
Company and model: Dell Precision T1700
CPU and clock speed: Intel(R) Xeon(R) CPU E3-1270 v3 @ 3.50GHz (8 cores)


[Part 2, Results Summary]
[No.]	[DocumentName] 		[Size] 	 	[Bandwidth]
1	Pride and Prejudice	726223 bytes	8 bits/ms
2	Metamorphosis		141420 bytes	9 bits/ms

[Part 1, Test Cases]
[Input of test 1]
write hal hobj 
read hal 
write lyle lobj 10
read hal lobj 
write lyle hobj 20
write hal lobj 200
read hal hobj
read lyle lobj
read lyle hobj
foo lyle lobj
Hi lyle, This is hal
The missile launch code is 1234567

[Output of test 1]
Bad Instruction
The current state is: 
   lobj: 0
   hobj: 0
   lyle read: 0
   hal read: 0

Bad Instruction
The current state is: 
   lobj: 0
   hobj: 0
   lyle read: 0
   hal read: 0

Bad Instruction
The current state is: 
   lobj: 0
   hobj: 0
   lyle read: 0
   hal read: 0

lyle writes 10 to lobj
The current state is: 
   lobj: 10
   hobj: 0
   lyle read: 0
   hal read: 0

hal reads lobj
The current state is: 
   lobj: 10
   hobj: 0
   lyle read: 0
   hal read: 10

lyle writes 20 to hobj
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 10

hal writes 200 to lobj
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 10

hal reads hobj
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 20

lyle reads lobj
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 10
   hal read: 20

lyle reads hobj
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 20

Bad Instruction
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 20

Bad Instruction
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 20

Bad Instruction
The current state is: 
   lobj: 10
   hobj: 20
   lyle read: 0
   hal read: 20

[Input of test 2]
write hal hobj 60
read hal hobj
read blah blah
read
read lyle hobj
write lyle lobj 15
read lyle lobj
read lyle hobj
write hal lobj 25 
write hal lobj hobj
hal read lyle
read hal lobj
hal

[Output of test 2]
hal writes 60 to hobj
The current state is: 
   lobj: 0
   hobj: 60
   lyle read: 0
   hal read: 0

hal reads hobj
The current state is: 
   lobj: 0
   hobj: 60
   lyle read: 0
   hal read: 60

Bad Instruction
The current state is: 
   lobj: 0
   hobj: 60
   lyle read: 0
   hal read: 60

Bad Instruction
The current state is: 
   lobj: 0
   hobj: 60
   lyle read: 0
   hal read: 60

lyle reads hobj
The current state is: 
   lobj: 0
   hobj: 60
   lyle read: 0
   hal read: 60

lyle writes 15 to lobj
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 60

lyle reads lobj
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 15
   hal read: 60

lyle reads hobj
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 60

hal writes 25 to lobj
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 60

Bad Instruction
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 60

Bad Instruction
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 60

hal reads lobj
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 15

Bad Instruction
The current state is: 
   lobj: 15
   hobj: 60
   lyle read: 0
   hal read: 15

