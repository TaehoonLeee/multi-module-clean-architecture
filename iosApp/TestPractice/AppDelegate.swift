//
//  AppDelegate.swift
//  TestPractice
//
//  Created by Taehoon Lee on 2022/12/15.
//

import UIKit
import Example

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        let viewController = Main_iosKt.createExampleViewController()
        window?.rootViewController = viewController
        window?.makeKeyAndVisible()
        
        return true
    }

}

