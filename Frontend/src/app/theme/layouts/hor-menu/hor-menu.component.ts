import { Component, OnInit, ViewEncapsulation, AfterViewInit } from '@angular/core';
import { Helpers } from '../../../helpers';

declare let mLayout: any;
@Component({
    selector: "app-hor-menu",
    templateUrl: "./hor-menu.component.html",
    encapsulation: ViewEncapsulation.None,
})
export class HorMenuComponent implements OnInit, AfterViewInit {

    user = JSON.parse(sessionStorage.getItem("user")) || JSON.parse(localStorage.getItem("user"));

    constructor() {

    }
    ngOnInit() {

    }
    ngAfterViewInit() {

        mLayout.initHeader();

    }

}