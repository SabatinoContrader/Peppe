import { NO_ERRORS_SCHEMA } from "@angular/core";
import { ParksMapComponent } from "./parksMap.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("ParksMapComponent", () => {

  let fixture: ComponentFixture<ParksMapComponent>;
  let component: ParksMapComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [ParksMapComponent]
    });

    fixture = TestBed.createComponent(ParksMapComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
