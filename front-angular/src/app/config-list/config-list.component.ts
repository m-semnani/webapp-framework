import { Observable } from "rxjs";
import { ConfigService, Config } from "./../service/config.service";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-config-list',
  templateUrl: './config-list.component.html',
  styleUrls: ['./config-list.component.css']
})
export class ConfigListComponent implements OnInit {

  configList: Observable<Config[]>;

  constructor(private configService: ConfigService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.configList = this.configService.getConfigList();
  }

  updateConfig(id: number){
    this.router.navigate(['config-update', id]);
  }
}
