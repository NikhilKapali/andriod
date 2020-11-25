# stw300cem-final-project-NikhilKapali
stw300cem-final-project-NikhilKapali created by GitHub Classroom

# Bakery Café & Shop

# Introduction
The Bakery café and shop application is an Android application whose main objective is to provide the users various types of services as 
well as to provide administrators multiple functions that can be used to manage daily based activities of the business. 
In this application customers are able to order food or reserve tables with a simple touch in their android device from 
anywhere as long as there is internet connection. Employees of the organization are also able to view and enter of customers data 
and also reservation data.  The main purpose for creating the Bakery cafe and shop application is to provide better services for 
both customers as well as employees in any manner that helps them.  

# Aims & Objectives
	To create an Android application that makes easier ordering of bakery items easier and faster from anywhere at any time.
	To manage information and data of anything related to the business and its customers more reliable and efficient to enhance the work of the employees as well as for better flow of data.
	To develop an Android application with an interface that is efficient, simple and user-friendly. 

# Features of the Project
	The customers can view the items provided by the café or shop.
	Ordering of bakery products or items can be done from anywhere from the application.
	Location of the café or shop can also be viewed by the application.
 Use of various sensors and hardwares as well as implementation of wearable devices.

# Backend Api Link
https://github.com/stw304cem/t2-backend-api-NikhilKapali.git

 Additional changes made to the Api backend

app.post('/ReviewUser', function (req, res) {
    var review = new Review(req.body);
    review.save().then(function () {
        res.send('Review Added Successfully');
    }).catch(function (e) {

    });
    console.log(req.body);
});

app.post('/FoodOrder', function (req, res) {
    var orderedFood = new FoodOrder(req.body);
    orderedFood.save().then(function () {
        res.send('Food Ordered Successfully');
    }).catch(function (e) {

    });
    console.log(req.body);
});
app.post('/BookTable', function (req, res) {
    var reservation = new Reservation(req.body);
    reservation.save().then(function () {
        res.send('Table Reserved Successfully');
    }).catch(function (e) {

    });
    console.log(req.body);
});
app.put('/userUpdate/:id', function (req, res) {
    const id = req.params.id.toString();
    User.findByIdAndUpdate(id, req.body, { new: true })
        .then(function (user) {
            console.log(user);
            res.status(201).json({
                message: "The Product was Updated successfully"
            });
        }).catch(function (err) {
            console.log(err);
            res.status(500).json({
                message: err
            });
        })
});

# Youtube Link

# RESTLINK (Retrofit2)

#Conclusion

The system or application will be conducting activities or tasks that are essential for a specified organization or a business. The application be performing various types of activities like recording and managing data related to the organization as well as information related to the organization’s customers. The application will be developed using a specified Android application development software called the Android studio. The programming done in Android studio will be a mixture of JAVA Language and Android application language (Since, Android has not officially declared a language type mixture of various languages are required to develop the system). Hence, the Android application will be developed following the statements declared in this document. 



