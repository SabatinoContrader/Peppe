import { NO_ERRORS_SCHEMA } from "@angular/core";
import { StopsHistoryComponent } from "./stops-history.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("StopsHistoryComponent", () => {

    let fixture: ComponentFixture<StopsHistoryComponent>;
    let component: StopsHistoryComponent;
    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
            ],
            declarations: [StopsHistoryComponent]
        });

        fixture = TestBed.createComponent(StopsHistoryComponent);
        component = fixture.componentInstance;

    });

    it("should be able to create component instance", () => {
        expect(component).toBeDefined();
    });

});
