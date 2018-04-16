//
//  WebViewNewsController.swift
//  IGNApp
//
//  Created by Trevor Yovaish on 4/3/18.
//  Copyright © 2018 Trevor Yovaish. All rights reserved.
//

import UIKit
import WebKit

class WebViewNewsController: UIViewController, WKUIDelegate {
    var contentURL : URL!
    var contentManager : ContentManager!
    @IBOutlet weak var webView: WKWebView!
    override func viewDidLoad() {
        super.viewDidLoad()
        let myRequest = URLRequest(url: contentURL!)
        webView.load(myRequest)
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
