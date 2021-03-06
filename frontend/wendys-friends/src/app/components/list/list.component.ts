import { Component, OnInit } from '@angular/core';
import { OwnerService } from "../../service/owner.service";
import { Owner } from "../../dto/owner";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  owners: Owner[];
  error = false;
  errorMessage = "";

  constructor(private ownerService: OwnerService) { }

  ngOnInit(): void {
    this.ownerService.getOwner().subscribe(
      owners => {
        this.owners = owners;
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
