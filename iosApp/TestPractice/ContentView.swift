//
//  ContentView.swift
//  TestPractice
//
//  Created by taehoon lee on 2023/03/10.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        TabView {
            ItemScreen()
                .tabItem {
                    Image(systemName: "list.bullet")
                }
            GalleryScreen()
                .tabItem {
                    Image(systemName: "magnifyingglass")
                }
            Text("Empty Screen")
                .tabItem {
                    Image(systemName: "person")
                }
        }
    }
}
