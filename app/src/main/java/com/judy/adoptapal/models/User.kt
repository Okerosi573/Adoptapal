package com.judy.adoptapal.models
class User {


    var email: String = ""
    var pass: String = ""
    var userid: String = ""
    var phone: String = ""
    var location: String = ""
    var fname: String = ""

    constructor(
        email: String, pass: String, userid: String,
        phone: String, location: String, fname : String
    ) {
        this.email = email
        this.pass = pass
        this.userid = userid
        this.phone = phone
        this.location = location
        this.fname = fname

    }


}