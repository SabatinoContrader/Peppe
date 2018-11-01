import { Component, OnInit, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
    selector: "app-index",
    // templateUrl: "./index.component.html",
    template: "",
    encapsulation: ViewEncapsulation.None,
})
export class IndexComponent implements OnInit {
    constructor(private router: Router) {
        var user = JSON.parse(sessionStorage.getItem("user"));
        if (!user) {
            router.navigateByUrl("login");
            return;
        }

        //var url = user.type === 0 ? "managementPark" : "findCarPlace";
        var url="";
        switch(user.type)
            {
                case 0: url = "managementPark"; break;
                case 1: url = "findCarPlace"; break;
                case 2: url = "findCarPlace"; break;
                case 3: url = "managementPark"; break;
            }

        router.navigateByUrl(url)
    }
    ngOnInit() {

    }
}