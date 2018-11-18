import { NO_ERRORS_SCHEMA } from "@angular/core";
import { StopsInProgressComponent } from "./stops-in-progress.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("StopsInProgressComponent", () => {

    let fixture: ComponentFixture<StopsInProgressComponent>;
    let component: StopsInProgressComponent;
    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
            ],
            declarations: [StopsInProgressComponent]
        });

        fixture = TestBed.createComponent(StopsInProgressComponent);
        component = fixture.componentInstance;

    });

    it("should be able to create component instance", () => {
        expect(component).toBeDefined();
    });

});
