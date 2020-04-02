import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Horse } from 'src/app/dto/horse';

@Component({
  selector: 'app-horse-search',
  templateUrl: './horse-search.component.html',
  styleUrls: ['./horse-search.component.scss']
})
export class HorseSearchComponent implements OnInit {
  @Output() searchHorse: EventEmitter<Horse> = new EventEmitter();

  name: string;
  notes: string;
  rating = 0;
  birthday: string;
  race: string;

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    const horse = new Horse(0, this.name, this.notes, this.rating, this.birthday, 0, this.race, null, null, null, null);

    console.log(horse);

    this.searchHorse.emit(horse);
  }
}
