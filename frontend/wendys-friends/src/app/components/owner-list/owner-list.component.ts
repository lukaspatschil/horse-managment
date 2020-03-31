import { Component, OnInit, Input } from '@angular/core';

import { Owner } from 'src/app/dto/owner';
import { HorseService } from 'src/app/service/horse.service';
import { Horse } from 'src/app/dto/horse';

@Component({
  selector: 'app-owner-list',
  templateUrl: './owner-list.component.html',
  styleUrls: ['./owner-list.component.scss']
})
export class OwnerListComponent implements OnInit {
  @Input() owner: Owner;

  error = false;
  errorMessage = "";
  horses: Horse[];

  constructor(private horseService: HorseService) { }

  ngOnInit(): void {
    this.horseService.getHorsesfromOwner(this.owner.id).subscribe(
      horses => {
        this.horses = horses;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = "The backend can not to be reached";
    } else if (error.status === 400) {
      // If status is 400, the input was wrong
      this.errorMessage = "The input was wrong";
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
