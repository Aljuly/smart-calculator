import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ComunicationService } from '../../../_services';
import { Description } from '../../../_models';

@Component({
  selector: 'app-defboard',
  templateUrl: './defboard.component.html',
  styleUrls: ['./defboard.component.css']
})
export class DefboardComponent implements OnInit {
  title: string;
  info: string;
  constructor(private comunicationService: ComunicationService, private crd: ChangeDetectorRef) { }
  ngOnInit(): void {
    // Be ready to optain actual data
    this.comunicationService.DescriptionMessage.subscribe((res) => {
      this.title = res.title;
      this.info = res.description;
      console.log(this.title + ' received in description!');
      // this.crd.detectChanges();
    });
  }
}

