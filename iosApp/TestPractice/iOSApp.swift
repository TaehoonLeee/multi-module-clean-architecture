//
//  AppDelegate.swift
//  TestPractice
//
//  Created by Taehoon Lee on 2022/12/15.
//

import Example
import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        IoCContainer.shared.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
