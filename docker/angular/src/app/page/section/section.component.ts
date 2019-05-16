import { Component, OnInit, OnDestroy, OnChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../../_services/data.service';
import { Description } from '../../_models';
import { JsonConvert, OperationMode } from 'json2typescript';
import { ComunicationService } from '../../_services';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {
  id: number;
  paramsSub: any;
  jsonConvert: JsonConvert;
  constructor(
    private activatedRoute: ActivatedRoute,
    private dataService: DataService,
    private comunicationService: ComunicationService
  ) {}
  ngOnInit() {
    // Check the detailed reference in the chapter "JsonConvert class properties and methods"
    this.jsonConvert = new JsonConvert();
    // this.jsonConvert.operationMode = OperationMode.LOGGING; // print some debug data
    this.jsonConvert.ignorePrimitiveChecks = false; // don't allow assigning number to string etc.
    // Get the selected operation
    this.activatedRoute.params.subscribe((params) => {
      // Lookup on the server for selected operation description
      this.dataService
      .get_description(params['id'] || 100000)
      .subscribe((res: any) => {
        // json converter returns an Array!
        const description = this.jsonConvert.deserialize(res, Description);
        console.log(description.id + ' broadcasted!');
        // Broadcast this description
        this.comunicationService.changeDescription(description);
      });
    });
  }
}
