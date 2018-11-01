import { Component, OnInit} from '@angular/core';
import { SlotService } from '../../../../_services/slot.service';
import { NgForm } from '@angular/forms';
import { Slot } from '../../../../_models/Slot';

@Component({
  selector: 'app-info-manage-park',
  templateUrl: './info-manage-park.component.html',
  styleUrls: ['./info-manage-park.component.scss']
})

export class InfoManageParkComponent implements OnInit {

  public slots: Array<Slot>;
  public selectedSlotID: number;
  public selectedSlot: Slot;
  public exists: boolean = false;
  public dummyCopySlot: Slot;

  constructor(private slotService: SlotService) { }

  ngOnInit() {
    this.slotService.getSlots().subscribe((response) => {
      this.slots = response;
    });
  }

  selectSlot(slot: Slot): void {
    this.exists = true;

    this.selectedSlot = slot;
    this.dummyCopySlot = Object.assign({}, slot);

    this.selectedSlotID = slot.id;

  }
}