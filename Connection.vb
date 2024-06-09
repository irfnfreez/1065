Imports System.Data.OleDb
Module Connection
    Public ds As New DataSet
    Public cmd As New OleDbCommand
    Public conn As New OleDbConnection("Provider=Microsoft.Jet.OLEDB.4.0;Data Source=C:\Users\User\Desktop\VB\testVB.mdb")
    Public dr As OleDbDataReader
    Public dt As DataTable
End Module
