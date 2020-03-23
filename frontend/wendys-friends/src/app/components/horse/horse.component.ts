import { Component, OnInit } from '@angular/core';
import {HorseService} from '../../service/horse.service';
import { Horse } from 'src/app/dto/horse';

@Component({
  selector: 'app-horse',
  templateUrl: './horse.component.html',
  styleUrls: ['./horse.component.scss']
})
export class HorseComponent implements OnInit {

  error = false;
  success = false;
  errorMessage = '';
  horses:Horse[];
  horse: Horse;

  constructor(private horseService: HorseService) { }

  ngOnInit(): void {
    this.horseService.getHorse().subscribe(horses => {
      this.horses = horses;
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
   * Saves a new horse with the parameters of the name, notes, rating and birthday as a Horse
   * @param horse the values of the new horse
   */
  public addHorse(horse:Horse) {
    this.horseService.addHorse(horse).subscribe(horse => {
      this.success = true;
      this.horse = horse;
    },
    error => {
      this.defaultServiceErrorHandling(error);
    });
  }

  /**
   * 
   * @param error 
   */
  public deleteHorse(horse: Horse) {
    // Remove From UI
    this.horses = this.horses.filter(o => o.id !== horse.id);
    // Remove from server
    this.horseService.deleteHorse(horse).subscribe();
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.status === 400) {
      // If status is 400, the input was wrong
      this.errorMessage = 'The input was wrong';
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
