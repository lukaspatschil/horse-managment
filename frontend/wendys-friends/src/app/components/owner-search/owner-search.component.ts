import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { OwnerService } from 'src/app/service/owner.service';
import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-owner-search',
  templateUrl: './owner-search.component.html',
  styleUrls: ['./owner-search.component.scss']
})
export class OwnerSearchComponent implements OnInit {
  @Output() searchOwner: EventEmitter<any> = new EventEmitter();

  success = false;
  error = false;
  errorMessage = "";
  name = "";
  owners: Owner[];

  constructor(private ownerService: OwnerService) { }

  ngOnInit(): void {
  }

    /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  /**
   * Success flag will be deactivated, which clears the success message
   */
  vanishSuccess() {
    this.success = false;
  }

  onSubmit() {
    const owner = {
      id: 0,
      name: this.name,
      createdAt: "",
      updatedAt: ""
    }

    this.searchOwner.emit(owner);
  }
}
