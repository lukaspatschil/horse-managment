import { Component, OnInit, Input, EventEmitter, Output} from '@angular/core';

import { Horse } from 'src/app/dto/horse';
import { Owner } from 'src/app/dto/owner';

@Component({
  selector: 'app-horse-item',
  templateUrl: './horse-item.component.html',
  styleUrls: ['./horse-item.component.scss']
})
export class ListHorseComponent implements OnInit {
  @Input() owners: Owner[];
  @Input() horse: Horse;
  @Output() deleteHorse: EventEmitter<Horse> = new EventEmitter();
  @Output() updateHorse: EventEmitter<Horse> = new EventEmitter();

  show:boolean;
  imageFile: File;
  fileList: FileList;

  constructor() { }

  ngOnInit(): void {
  }

  onDelete(horse) {
    this.deleteHorse.emit(horse);
  }

  onShow() {
    this.show = !this.show;
  }

  onUpdate() {
    const horse = new Horse(this.horse.id, this.horse.name, this.horse.notes, this.horse.rating, this.horse.birthday, this.horse.owner, this.horse.race, this.horse.image, this.horse.type, null, null);
    console.log(this.horse);
    this.updateHorse.emit(horse);

    window.location.reload();
  }

  picked(event: any) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      this.imageFile = file;
      this.handleInputChange(file); // turn into base64
    } else {
      alert("No file selected");
    }
  }

  handleInputChange(files: any) {
    const file = files;
    const reader = new FileReader();

    reader.onloadend = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e: any) {
    const reader = e.target;
    const base64result = reader.result.substr(reader.result);

    this.horse.type = base64result.substr(0, base64result.indexOf(",") + 1);
    this.horse.image = base64result.substr(base64result.indexOf(",") + 1);
  }
}
