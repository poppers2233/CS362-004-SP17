#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include "rngs.h"


int main(int argc, char *argv[]){
        int r;
        int check;
	int players = atoi(argv[1]);
        printf("supplyCount unit test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

         r = initializeGame(players, k, 1, &G);

        
	int cop = supplyCount(copper,&G);
	int sil = supplyCount(silver,&G);
	int gol   = supplyCount(gold,&G);

        if(cop == 60 - (7*players)){
                printf("PASS\n");
                return 0;
        }
	else{
		printf("FAIL\n");
		return -1;	
	}
	
	 if(sil == 40){
                printf("PASS\n");
                return 0;
        }
        else{
                printf("FAIL\n");
                return -1;
        }
	
	 if(gol  == 30 ){
                printf("PASS\n");
                return 0;
        }
        else{
                printf("FAIL\n");
                return -1;
        }



        return 0;
}
