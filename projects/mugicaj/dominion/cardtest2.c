
#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"


int main(){
        int r;
        int check;
	
	int count=0;
	int count2=0;

        printf("adventurer unit test ----\n");
        int k[10] = {adventurer, council_room, feast, gardens, mine,
               remodel, smithy, village, baron, great_hall};

        struct gameState G;

         r = initializeGame(2, k, 1, &G);

       	int i; 
	for(i=0; i< sizeof(G.hand[0])/4;i++){
		if(G.hand[0][i] == copper){
			count++;	
		}
	
	}
	
	check = cardEffect(adventurer,0,0,0,&G,0,0);
	
        for(i=0; i< sizeof(G.hand[0])/4;i++){
                if(G.hand[0][i] == copper){
                        count2++;
                }

        }
	
        if(count+2 == count2){

                printf("PASS\n");
                return 0;

        }

        else{
                printf("FAILED\n");
                return -1;
        }

        return 0;

}

