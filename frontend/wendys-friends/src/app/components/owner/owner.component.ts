import {Component, OnInit} from '@angular/core';
import {OwnerService} from '../../service/owner.service';
import {Owner} from '../../dto/owner';

@Component({
  selector: 'app-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.scss']
})
export class OwnerComponent implements OnInit {

  success = false;
  error = false;
  errorMessage = '';
  owner:Owner;
  owners:Owner[];

  constructor(private ownerService: OwnerService) {
  }

  ngOnInit():void {
    this.ownerService.getOwner().subscribe(owners => {
      this.owners = owners;
    },
    error => {
      this.defaultServiceErrorHandling(error);
    });
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

  /**
   * Loads the owner for the specified id
   * @param id the id of the owner
   */
   private loadOwner(id: number) {
    this.ownerService.getOwnerById(id).subscribe(
      (owner: Owner) => {
        this.owner = owner;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  /**
   * Saves a new owner with a name
   * @param name the name of the new owner
   */
  public addOwner(owner: Owner) {
    this.ownerService.addOwner(owner).subscribe(owner => {
      this.owners.push(owner);
      this.owner = owner;
      this.success = true;
    },
    error => {
      this.defaultServiceErrorHandling(error);
    });
  }

  /**
   * 
   * @param error 
   */
  public deleteOwner(owner: Owner) {
    // Remove From UI
    this.owners = this.owners.filter(o => o.id !== owner.id);
    // Remove from server
    this.ownerService.deleteOwner(owner).subscribe();
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend can not to be reachable';
    } else if (error.status === 400) {
      // If status is 400, the input was wrong
      this.errorMessage = 'The input was wrong!';
    } else if (error.status === 500) {
      // If status is 500, there was a server error
      this.errorMessage = 'There was an unexpected server error';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }
}
