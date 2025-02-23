Selenium Project
  i used a POM design pattern to identifiy all the locators in the page and the functions which is related to the specific page.
  i created a BasrPage which contain all the common function and all pages will inherate from this class

  as a prerequest we make sure that the we are in the Home Page and we are using the English Language, then we navigate to the All Video Games page, 
  then we filtered out the results by the new and free shipping, then we sort the results by High to Low.

  after this we iterate on the all products in the page and each product has price under 15 k EGP we click on it and add it to our cart without any additional protection
  then we navigate back to the filtered page to keep iterating on the products after this we need to make sure that the items added successfully on the cart we check the
  total amount and if it's still zero so we need to navigate back and click on the next button but if the products were added successfully we need to make sure that the
  amount of money is equal the sum of the products under 15000 EGP

  Note: we can't proceed to the next step in the purches process because we can't create a new account because it's need a new phone number

Rest Assured Project
  in this project we care about the ordering so we give each test case a priority to keep sure that the post api will be the first api because we will use the response
  in the get request. So the we sent the post request then fetch the ID from the response and also we validated on the status code it should be 201 (Created) and the
  response body also.

  we faced an issue that the post api didn't write in any database so we couldn't use the same id in the get request so we we send the get request with id which is already
  exist and we validated on the status code to be 200 

  then we send a put request with the id which we used in the get request and we updated the values of this id and validated on the status code and the data after the update


  
