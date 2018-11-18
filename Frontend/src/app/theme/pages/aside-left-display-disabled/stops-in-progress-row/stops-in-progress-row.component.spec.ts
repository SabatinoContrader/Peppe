import { NO_ERRORS_SCHEMA } from "@angular/core";
import { StopsInProgressRowComponent } from "./stops-in-progress-row.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("StopsInProgressRowComponent", () => {

    let fixture: ComponentFixture<StopsInProgressRowComponent>;
    let component: StopsInProgressRowComponent;
    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
            ],
            declarations: [StopsInProgressRowComponent]
        });

        fixture = TestBed.createComponent(StopsInProgressRowComponent);
        component = fixture.componentInstance;

    });

    it("should be able to create component instance", () => {
        expect(component).toBeDefined();
    });

});
