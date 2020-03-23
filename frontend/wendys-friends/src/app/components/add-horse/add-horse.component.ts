import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-add-horse',
  templateUrl: './add-horse.component.html',
  styleUrls: ['./add-horse.component.scss']
})
export class AddHorseComponent implements OnInit {
  @Output() addHorse: EventEmitter<any> = new EventEmitter();

  name:string;
  notes:string;
  rating:number;
  birthday:string;

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    const horse = {
      name: this.name,
      notes: this.notes,
      rating: this.rating,
      birthday: this.birthday
    }

    console.log(horse);

    this.addHorse.emit(horse);
  }

}
