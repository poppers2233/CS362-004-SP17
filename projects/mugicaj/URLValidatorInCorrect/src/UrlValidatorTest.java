/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.
   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
                               new ResultPair("ftp://", true),
                               new ResultPair("h3t://", true),
                               new ResultPair("3ht://", false),
                               new ResultPair("http:/", false),
                               new ResultPair("http:", false),
                               new ResultPair("http/", false),
                               new ResultPair("://", false),
                               new ResultPair("", true)};

   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com", true),
                                  new ResultPair("go.com", true),
                                  new ResultPair("go.au", true),
                                  new ResultPair("0.0.0.0", true),
                                  new ResultPair("255.255.255.255", true),
                                  new ResultPair("256.256.256.256", false),
                                  new ResultPair("255.com", true),
                                  new ResultPair("1.2.3.4.5", false),
                                  new ResultPair("1.2.3.4.", false),
                                  new ResultPair("1.2.3", false),
                                  new ResultPair(".1.2.3.4", false),
                                  new ResultPair("go.a", false),
                                  new ResultPair("go.a1a", false),
                                  new ResultPair("go.cc", true),
                                  new ResultPair("go.1aa", false),
                                  new ResultPair("aaa.", false),
                                  new ResultPair(".aaa", false),
                                  new ResultPair("aaa", false),
                                  new ResultPair("", false)
   };
   ResultPair[] testUrlPort = {new ResultPair(":80", true),
                             new ResultPair(":65535", true),
                             new ResultPair(":0", true),
                             new ResultPair("", true),
                             new ResultPair(":-1", false),
                             new ResultPair(":65636", true),
                             new ResultPair(":65a", false)
   };
   ResultPair[] testPath = {new ResultPair("/test1", true),
                          new ResultPair("/t123", true),
                          new ResultPair("/$23", true),
                          new ResultPair("/..", false),
                          new ResultPair("/../", false),
                          new ResultPair("/test1/", true),
                          new ResultPair("", true),
                          new ResultPair("/test1/file", true),
                          new ResultPair("/..//file", false),
                          new ResultPair("/test1//file", false)
   };
    ResultPair[] testUrlQuery = {new ResultPair("?action=view", true),
                              new ResultPair("?action=edit&mode=up", true),
                              new ResultPair("", true)
   };
    Object[] testUrlParts = {testUrlScheme, testUrlAuthority, testUrlPort, testPath, testUrlQuery};
    
   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           
           System.out.println("Return True set:");
      
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
           System.out.println(urlVal.isValid("http://www.thisIsRandomButTrue.com"));
           System.out.println(urlVal.isValid("http://google.com"));
           System.out.println(urlVal.isValid("http://192.168.16.1"));
           System.out.println(urlVal.isValid("https://www.ietf.org/rfc/rfc1738.txt"));
           System.out.println(urlVal.isValid("http://google.com:80"));
 	   System.out.println(urlVal.isValid("http://google.com/path"));
 	   System.out.println(urlVal.isValid("http://google.com/path/"));
 	   System.out.println(urlVal.isValid("http://google.com/#anchor"));
 	   System.out.println(urlVal.isValid("http://google.com/$123"));
 	   System.out.println(urlVal.isValid("http://google.com/path/file"));
 	   System.out.println(urlVal.isValid("http://google.com/path?query=true"));
 	   System.out.println(urlVal.isValid("http://google.com?query=true"));
  	   System.out.println(urlVal.isValid("http://google.com:80/path?query=true"));
           
           System.out.println("Return False sets");
           System.out.println(urlVal.isValid("http:/amazon.com"));
 	   System.out.println(urlVal.isValid("http:amazon.com"));
 	   System.out.println(urlVal.isValid("amazon.com"));
 	   System.out.println(urlVal.isValid("http://google"));
 	   System.out.println(urlVal.isValid("http://google."));
 	   System.out.println(urlVal.isValid("http://google.xxx"));
 	   System.out.println(urlVal.isValid("http://google.3b2"));
	   System.out.println(urlVal.isValid("http://;$#.com"));
	   System.out.println(urlVal.isValid("http://.com"));
 	   System.out.println(urlVal.isValid("http://1.2.3.4.5"));
 	   System.out.println(urlVal.isValid("http://.1.2.3.4.5"));
 	   System.out.println(urlVal.isValid("http://1.2.3.4.5."));
 	   System.out.println(urlVal.isValid("http://1.2.3.4.a"));
 	   System.out.println(urlVal.isValid("http://google.com:-1"));
 	   System.out.println(urlVal.isValid("http://google.com:80b"));
 	   System.out.println(urlVal.isValid("http://google.com/.."));
 	   System.out.println(urlVal.isValid("http://google.com//path"));
 	   System.out.println(urlVal.isValid("http://google.com/path//file"));
	   
	   
   }
   
   // we will partition the code focusing on testing the differnts parts of a URL in order to asses 
   // where specific bugs are located.
   
   //First partition: testing Schemes per as documentation sepficied valid/invalid
   public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           
           //results are part of our Result Pair
           System.out.println("______SCHEME_______");
           for(int i=0; i < testUrlScheme.length;i++){
               String test = testUrlScheme[i].item + "website.com";
               boolean ret = urlVal.isValid(test);
               boolean exp = testUrlScheme[i].valid;
               boolean res = ret == exp;
               System.out.println(test);
               if(res)
                   System.out.println("PASS");
               else{
                   
                   System.out.println("FAIL");
                   System.out.println("expected: "+ ret);
                   System.out.println("returned: "+ exp);
                   }
              }
           System.out.println("______SCHEME_______");
   }
   
   //Second Partition Authority testing
   public void testYourSecondPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           
           //results are part of our Result Pair
           System.out.println("______AUTHORITEH_______");
           for(int i=0; i < testUrlAuthority.length;i++){
               String test = "https://" + testUrlAuthority[i].item ;
               boolean ret = urlVal.isValid(test);
               boolean exp = testUrlAuthority[i].valid;
               boolean res = ret == exp;
               System.out.println(test);
               if(res)
                   System.out.println("PASS");
               else{
                   
                   System.out.println("FAIL");
                   System.out.println("returned: " + ret);
                   System.out.println("expected: " + exp);
                   }
              }
           System.out.println("______AUTHORITEH_______");
   }
   
   public void testYourThirdPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           
           //results are part of our Result Pair
           System.out.println("______PORT_______");
           for(int i=0; i < testUrlPort.length;i++){
               String test = "https://website.com" + testUrlPort[i].item ;
               boolean ret = urlVal.isValid(test);
               boolean exp = testUrlPort[i].valid;
               boolean res = ret == exp;
               System.out.println(test);
               if(res)
                   System.out.println("PASS");
               else{
                   
                   System.out.println("FAIL");
                   System.out.println("returned: " + ret);
                   System.out.println("expected: " + exp);
                   }
              }
           System.out.println("______PORT_______");
   }
   public void testYourFourthPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           
           //results are part of our Result Pair
           System.out.println("______PATH_______");
           for(int i=0; i < testPath.length;i++){
               String test = "https://website.com" + testPath[i].item ;
               boolean ret = urlVal.isValid(test);
               boolean exp = testPath[i].valid;
               boolean res = ret == exp;
               System.out.println(test);
               if(res)
                   System.out.println("PASS");
               else{
                   
                   System.out.println("FAIL");
                   System.out.println("returned: " + ret);
                   System.out.println("expected: " + exp);
                   }
              }
           System.out.println("______PATH_______");
   }
   
   public void testYourFifthPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           
           //results are part of our Result Pair
           System.out.println("______QUERY_______");
           for(int i=0; i < testUrlQuery.length;i++){
               String test = "https://website.com" + testUrlQuery[i].item ;
               boolean ret = urlVal.isValid(test);
               boolean exp = testUrlQuery[i].valid;
               boolean res = ret == exp;
               System.out.println(test);
               if(res)
                   System.out.println("PASS");
               else{
                   
                   System.out.println("FAIL");
                   System.out.println("returned: " + ret);
                   System.out.println("expected: " + exp);
                   }
              }
           System.out.println("______QUERY_______");
   }
   
   public void testIsValid()
   {
          UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           //scheme, authority, port, path, query.
          System.out.println("---------TEST IS VALID-----------");
           int[] indexes = new int[5];
           indexes[0] = testUrlScheme.length;
           indexes[1] = testUrlAuthority.length;
           indexes[2] = testUrlPort.length;
           indexes[3] = testPath.length;
           indexes[4] = testUrlQuery.length;
           
           int[] indexesCheck = new int[5];
	   for(int i = 0;i<testUrlAuthority.length;i++)
	   {
               
               for(int a=0; a < 5; a++){
                   if(i >= indexes[a])
                      indexesCheck[a] = 0;
                   else
                      indexesCheck[a] = i;
               }
               System.out.println(               testUrlScheme   [indexesCheck[0]].item
                                               + testUrlAuthority[indexesCheck[1]].item
                                               + testUrlPort     [indexesCheck[2]].item
                                               + testPath        [indexesCheck[3]].item
                                               + testUrlQuery    [indexesCheck[4]].item);
               System.out.println("returned :" +
                                  urlVal.isValid(testUrlScheme   [indexesCheck[0]].item
                                               + testUrlAuthority[indexesCheck[1]].item
                                               + testUrlPort     [indexesCheck[2]].item
                                               + testPath        [indexesCheck[3]].item
                                               + testUrlQuery    [indexesCheck[4]].item));
               System.out.println("expected :" 
                                               +(testUrlScheme    [indexesCheck[0]].valid
                                               && testUrlAuthority[indexesCheck[1]].valid
                                               && testUrlPort     [indexesCheck[2]].valid
                                               && testPath        [indexesCheck[3]].valid
                                               && testUrlQuery    [indexesCheck[4]].valid));
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	 UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
           //scheme, authority, port, path, query
           System.out.println("----------------FULL COMPARISON CHECK-----------------");
	   for(int i = 0; i< testUrlAuthority.length;i++){
             for(int a=0; a < testUrlScheme.length;a++){
                 for(int b=0; b < testUrlPort.length;b++){
                     for(int c=0; c < testPath.length; c++){
                        for(int d=0; d < testUrlQuery.length;d++){
                            System.out.println(  testUrlScheme   [a].item
                                               + testUrlAuthority[i].item
                                               + testUrlPort     [b].item
                                               + testPath        [c].item
                                               + testUrlQuery    [d].item);
                            System.out.println("returned :" +
                                  urlVal.isValid(testUrlScheme   [a].item
                                               + testUrlAuthority[i].item
                                               + testUrlPort     [b].item
                                               + testPath        [c].item
                                               + testUrlQuery    [d].item));
                            System.out.println("expected: "
                                               + (testUrlScheme   [a].valid
                                               && testUrlAuthority[i].valid
                                               && testUrlPort     [b].valid
                                               && testPath        [c].valid
                                               && testUrlQuery    [d].valid));
                            
                        }
                     }
                 }
             }  
             
	   }
   }  
   
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   
    public static void main(String[] args) {
        UrlValidatorTest test = new UrlValidatorTest("testing");
        test.testManualTest();
        test.testYourFirstPartition();
        test.testYourSecondPartition();
        test.testYourThirdPartition();
        test.testYourFourthPartition();
        test.testYourFifthPartition();
        
        test.testIsValid();
        
        test.testAnyOtherUnitTest();
    }
}