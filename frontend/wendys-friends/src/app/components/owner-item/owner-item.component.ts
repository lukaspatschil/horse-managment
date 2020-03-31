import { Component, OnInit, Input, EventEmitter, Output } from "@angular/core";
import { OwnerService } from "../../service/owner.service";

import { Owner } from "src/app/dto/owner";

@Component({
  selector: "app-owner-item",
  templateUrl: "./owner-item.component.html",
  styleUrls: ["./owner-item.component.scss"]
})
export class ListOwnerComponent implements OnInit {
  @Input() owner: Owner;
  @Output() deleteOwner: EventEmitter<Owner> = new EventEmitter();
  @Output() updateOwner: EventEmitter<Owner> = new EventEmitter();

  show: boolean;

  constructor(
    private ownerService: OwnerService
  ) {}

  ngOnInit(): void {
    this.show = false;
  }

  onDelete(owner) {
    this.deleteOwner.emit(owner);
  }

  onShow() {
    this.show = !this.show;
  }

  onUpdate() {
    const owner = {
      id: this.owner.id,
      name: this.owner.name,
      createdAt: null,
      updatedAt: null
    }
    console.log(owner);
    this.updateOwner.emit(owner);

    window.location.reload();
  }
}
