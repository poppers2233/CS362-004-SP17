CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)

testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

unittestresults.out: runUnitTest1 runUnitTest2 runUnitTest3 runUnitTest4 runCardTest1 runCardTest2 runCardTest3 runCardTest4
	./runUnitTest1 &> unittestresult.out
	gcov unittest.c  >> unittestresult.out
	cat unittest.c.gcov >> unittestresult.out
	
	./runUnitTest2 &> unittestresult.out
	gcov unittest2.c  >> unittestresult.out
	cat unittest2.c.gcov >> unittestresult.out
	
	./runUnitTest3 &> unittestresult.out
	gcov unittest3.c  >> unittestresult.out
	cat unittest3.c.gcov >> unittestresult.out									
	./runUnitTest4 &> unittestresult.out
	gcov unittest4.c  >> unittestresult.out
	cat unittest4.c.gcov >> unittestresult.out
	
	./runCardTest1 &> unittestresult.out
	gcov cardtest1.c  >> unittestresult.out
	cat cardtest1.c.gcov >> unittestresult.out
	
	./runCardTest2 &> unittestresult.out
	gcov cardtest2.c  >> unittestresult.out
	cat cardtest2.c.gcov >> unittestresult.out
	
	./runCardTest3 &> unittestresult.out
	gcov cardtest3.c  >> unittestresult.out
	cat cardtest3.c.gcov >> unittestresult.out
	
	./runCardTest4 &> unittestresult.out
	gcov cardtest4.c  >> unittestresult.out
	cat cardtest4.c.gcov >> unittestresult.out

runUnitTest1: unittest.c dominion.o rngs.o
	gcc -o runUnitTest1 -g unittest.c  dominion.o rngs.o $(CFLAGS)

runUnitTest2: unittest2.c dominion.o rngs.o
	gcc -o runUnitTest2 -g unittest2.c  dominion.o rngs.o $(CFLAGS)

runUnitTest3: unittest3.c dominion.o rngs.o
	gcc -o runUnitTest3 -g unittest3.c  dominion.o rngs.o $(CFLAGS)

runUnitTest4: unittest4.c dominion.o rngs.o
	gcc -o runUnitTest4 -g unittest4.c  dominion.o rngs.o $(CFLAGS)

runCardTest1: cardtest1.c dominion.o rngs.o
	gcc -o runCardTest1 -g cardtest1.c  dominion.o rngs.o $(CFLAGS)

runCardTest2: cardtest2.c dominion.o rngs.o
	gcc -o runCardTest2 -g cardtest2.c  dominion.o rngs.o $(CFLAGS)

runCardTest3: cardtest3.c dominion.o rngs.o
	gcc -o runCardTest3 -g cardtest3.c  dominion.o rngs.o $(CFLAGS)

runCardTest4: cardtest4.c dominion.o rngs.o
	gcc -o runCardTest4 -g cardtest4.c  dominion.o rngs.o $(CFLAGS)

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player testDrawCard testBuyCard badTestDrawCard

clean:
	rm -f *.o playdom.exe playdom test.exe test player player.exe testInit testInit.exe *.gcov *.gcda *.gcno *.so
