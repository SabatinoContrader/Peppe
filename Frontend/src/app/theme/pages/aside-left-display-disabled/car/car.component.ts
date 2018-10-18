import { Component, OnInit, ChangeDetectorRef } from "@angular/core";
import { Router } from "@angular/router";
import { CarService } from "../../../../_services/car.service";
import { Car } from "../../../../_models/Car";


@Component({
    selector: "app-car",
    templateUrl: "./car.component.html",
    styleUrls: ["./car.component.scss"]
})

export class CarComponent implements OnInit {
    carsList: Car[];
    result: string;

    constructor(private carService: CarService, private router: Router, private ref: ChangeDetectorRef) {

    }

    ngOnInit() {
        this.result = this.carService.feedback;
        this.carService.myCarsList().subscribe(response => { this.carsList = response });
    }

    deleteCar(id: number) {
        this.carService.deleteCar(id).subscribe(response => {
            if (response == 0)
                this.result = "Auto in sosta! Devi terminare la sosta prima di poterla eliminare";
            else {
                this.result = "Auto eliminata con successo";
                this.carService.myCarsList().subscribe(response => {
                    console.log("QUA");
                    this.carsList = response;
                    this.ref.detectChanges();
                });
            }
        });
    }

}
