import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";

import { Router } from '@angular/router';
import { CarService } from "../../../../_services/car.service";

@Component({
    selector: "app-addCar",
    templateUrl: "./addCar.component.html",
    styleUrls: ["./addCar.component.scss"]
})

export class AddCarComponent implements OnInit {
    feedback: string;

    constructor(private carService: CarService, private router: Router) {

    }

    ngOnInit() {
        this.carService.deleteFeedback();
    }

    addNewCar(f: NgForm): void {
        this.carService.addNewCar(f.value.name).subscribe(response => {
            this.feedback = "Auto aggiunta con successo";
            // this.carService.changeFeedback(this.feedback);
            this.router.navigateByUrl("/car");
        });
    }
}
