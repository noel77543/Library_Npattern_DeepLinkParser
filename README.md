# Library_Npattern_DeepLinkParser
以物件Reflect方式取得DeepLink持有的參數值



### 引用import
- build.gradle(Project:YourProject)
  - Add it in your root build.gradle at the end of repositories

        allprojects {
          repositories {
            ...
            maven { url 'https://jitpack.io' }
          }
        }
    
    
- build.gradle(Module:app)
  - Add the dependency
  
        dependencies {
                implementation 'com.github.noel77543:Library_Npattern_DeepLinkParser:v1.0.1'
        }

---

### 使用方式use
假設有一DeepLink為

> String link = "myApp://Q/grouping/profile?user_name=noel&user_id=2122345521";

###### 方法一 

- 不使用@NParameter

  宣告一個物件為TestData1
  如:
     
       public class TestData1 {
          //變數名稱需等同link中藥取得的該變數user_name
          private String user_name;
          //變數名稱需等同link中藥取得的該變數user_id
          private String user_id;

          public String getName() {
              return user_name;
          }

          public String getId() {
              return user_id;
          }
       }
  
  

###### 方法二 

- 使用@NParameter

  宣告一個物件為TestData2
  如:
     
       public class TestData2 {
         @NParameter("user_name")
         private String name;
         @NParameter("user_id")
         private String id;

         public String getName() {
             return name;
         }

         public String getId() {
             return id;
         }
      }

最後在要進行解析的class中調用以下function
       
      
        TestData testData = new DeepLinkParser().parse(link, TestData.class);
         //deepLinkParser.parse執行後呼叫 testData1的getter 即可取出該變數值做事
         TestData1 testData1 = deepLinkParser.parse(link, TestData1.class);
         Log.e("name", testData1.getName() + "");
         Log.e("id", testData1.getId() + "");
         
         //deepLinkParser.parse執行後呼叫 testData2的getter 即可取出該變數值做事
         TestData2 testData2 = deepLinkParser.parse(link, TestData2.class);
         Log.e("name", testData2.getName() + "");
         Log.e("id", testData2.getId() + "");
---

### 備註remark
- @NParameter 非必要，只有在你希望你宣告的變數與DeepLink持有的變數名稱不一樣時才需使用


