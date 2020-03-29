import { Component, OnInit, Input, EventEmitter, Output } from "@angular/core";
import { OwnerService } from "../../service/owner.service";
import { HorseService } from "../../service/horse.service";

import { Owner } from "src/app/dto/owner";
import { Horse } from "src/app/dto/horse";

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
  name: string;
  horses: Horse[];

  constructor(
    private ownerService: OwnerService,
    private horseService: HorseService
  ) {}

  ngOnInit(): void {
    this.show = false;
    this.name = this.owner.name;

    this.horseService.getHorsesfromOwner(this.owner.id).subscribe(
      horses => {
        this.horses = horses;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  onDelete(owner) {
    this.deleteOwner.emit(owner);
  }

  onShow() {
    this.show = !this.show;
  }

  onUpdate() {
    this.owner.name = this.name;
    console.log(this.owner);
    this.updateOwner.emit(this.owner);
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = "The backend can not to be reached";
    } else if (error.status === 400) {
      // If status is 400, the input was wrong
      this.errorMessage = "The input was wrong!";
    } else if (error.status === 500) {
      // If status is 500, there was a server error
      this.errorMessage = "There was an unexpected server error";
    } else if (error.error.message === "No message available") {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }
}
