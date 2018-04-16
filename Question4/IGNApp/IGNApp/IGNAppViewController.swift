//
//  IGNAppViewController.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 3/21/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import UIKit

class IGNAppViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier=="webView"{
            let content = sender as! Content
            let webView = segue.destination as! WebViewNewsController
            webView.contentURL = ContentURLExtractor.getContentWebURL(content: content)
        }
    }
 

}
